package org.walkerljl.retry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.testng.annotations.Test;
import org.walkerljl.retry.abstracts.AbstractRetryBroker;
import org.walkerljl.retry.abstracts.AbstractRetryServer;
import org.walkerljl.retry.impl.RetryJobLoader;
import org.walkerljl.retry.impl.defaults.DefaultRemoteRetryJobQueue;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobDispatcher;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobLoader;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.util.JSONUtil;
import org.walkerljl.retry.impl.util.NamedThreadFactory;
import org.walkerljl.retry.impl.util.RetryIntervalCalculator;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.UnlockRetryJobParam;
import org.walkerljl.retry.service.User;
import org.walkerljl.retry.service.UserService;
import org.walkerljl.retry.service.impl.UserServiceImpl;
import org.walkerljl.retry.service.impl.retry.SyncUserInfoRetryHandler;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * RetryTest
 *
 * @author xingxun
 */
public class RetryTest extends BaseUnitTest {

    @Test
    public void test() throws InterruptedException {

        BlockingQueue<RetryJob> retryJobQueue = new ArrayBlockingQueue<>(100);

        RetryServer retryServer = new DefaultRetryServer(retryJobQueue);
        retryServer.start();

        Thread.sleep(10 * 1000);
    }
}

class DefaultRetryServer extends AbstractRetryServer implements RetryServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRetryServer.class);

    private RetryConfig             retryConfig;
    private RetryService            retryService;
    private RetryJobLoader          retryJobLoader;
    private RetryJobDispatcher      retryJobDispatcher;
    private RetryBroker             retryBroker;
    private RemoteRetryJobQueue remoteRetryJobQueue;
    private BlockingQueue<RetryJob> retryJobQueue;

    public DefaultRetryServer(BlockingQueue<RetryJob> retryJobQueue) {
        this.retryJobQueue = retryJobQueue;
    }

    @Override
    public void processInit() throws CannotInitResourceException {

        super.processInit();

        this.retryConfig = new RetryConfig();
        this.retryService = new DefaultRetryService();
        this.retryJobLoader = new DefaultRetryJobLoader(retryConfig, retryService);
        this.retryJobDispatcher = new DefaultRetryJobDispatcher(retryConfig, retryService);
        this.retryBroker = new DefaultRetryBroker();
        this.remoteRetryJobQueue = new DefaultRemoteRetryJobQueue(retryJobQueue);
    }

    @Override
    public void processStart() throws CannotStartMachineException {

        super.processStart();

        //RetryJobLoader
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    retryJobLoader.load(remoteRetryJobQueue);
                } catch (Throwable e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }, 1000, 1000);

        //RetryJobDispatcher
        new NamedThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                RetryJob retryJob = null;
                while (isRunning()) {
                    try {
                        retryJob = retryJobQueue.take();
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                    retryJobDispatcher.dispatch(retryJob);
                }
             }
        });

        //RetryBroker
        User user = new User("xingxun", "行寻");
        //在事务中执行
        String retryJobId = retryBroker.submit(buildRetryJob());
        UserService userService = new UserServiceImpl();
        userService.save(user);
        //另外一个RPC
        userService.sync(user);
        retryBroker.markRetryJobToCompleted(retryJobId);

        retryBroker.submit(buildRetryJob());
    }

    @Override
    public RetryJobLoader getRetryJobLoader() {
        return retryJobLoader;
    }

    @Override
    public RetryJobDispatcher getRetryJobDispatcher() {
        return null;
    }

    private RetryJob buildRetryJob() {

        RetryJob retryJob = new RetryJob();

        retryJob.setBizType("hello");
        retryJob.setBizId(UUID.randomUUID().toString());
        retryJob.setPriority(RetryPriorityEnum.LOW);
        retryJob.setTargetIdentifier(SyncUserInfoRetryHandler.class.getSimpleName());
        retryJob.setRetryRule("2,1");
        retryJob.setAttempts(0);
        retryJob.setMaxAttempts(10);
        retryJob.setStatus(RetryJobStatusEnum.UNPROCESS);
        retryJob.setLastRetryTime(new Date());
        Date nextRetryTime = RetryIntervalCalculator.calculateNextRetryTime(
                retryJob.getRetryRule(),
                retryJob.getAttempts()
        );
        retryJob.setNextRetryTime(nextRetryTime);

        retryJob.setCreatedTime(new Date());
        retryJob.setCreator("xingxun");
        retryJob.setModifiedTime(retryJob.getCreatedTime());
        retryJob.setModifier(retryJob.getCreator());

        List<RetryParam> retryParams = new ArrayList<>(1);
        retryJob.setParams(retryParams);
        RetryParam retryParam = new RetryParam();
        retryParams.add(retryParam);
        User user = new User();
        user.setId("xingxun");
        user.setName("行寻");
        retryParam.setValue(JSONUtil.toJSONString(user));
        retryParam.setStatus(RetryParamStatusEnum.NORMAL);
        retryParam.setCreatedTime(new Date());
        retryParam.setCreator("xingxun");
        retryParam.setCreatedTime(retryJob.getCreatedTime());
        retryParam.setModifier(retryJob.getModifier());
        retryParam.setModifiedTime(retryJob.getCreatedTime());

        return retryJob;
    }

    /**
     * Getter method for property <tt>retryJobQueue</tt>.
     *
     * @return property value of retryJobQueue
     */
    public BlockingQueue<RetryJob> getRetryJobQueue() {
        return retryJobQueue;
    }
}

class DefaultRetryService implements RetryService {

    @Override
    public RetryJob getRetryJobById(String retryJobId) {
        return null;
    }

    @Override
    public boolean lockRetryJob(LockRetryJobParam lockRetryJobParam) {
        return false;
    }

    @Override
    public boolean unlockRetryJob(UnlockRetryJobParam unlockRetryJobParam) {
        return false;
    }

    @Override
    public void saveRetryLog(RetryLog retryLog) {

    }

    @Override
    public List<RetryJob> listUnprocessRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<RetryJob> listFailureRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<RetryJob> listTimeoutRetryJobs(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize) {
        return null;
    }
}

class DefaultRetryBroker extends AbstractRetryBroker implements RetryBroker {

    @Override
    public String processSubmit(RetryJob retryJob) {
        return null;
    }

    @Override
    public void processMarkRetryJobToCompleted(String retryJobId) {

    }
}
