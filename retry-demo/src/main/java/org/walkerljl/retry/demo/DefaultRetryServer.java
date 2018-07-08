package org.walkerljl.retry.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.configuration.client.impl.readonly.PropertiesConfiguratorProvider;
import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.RetryJobDispatcher;
import org.walkerljl.retry.RetryServer;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.abstracts.AbstractRetryServer;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.daointerface.impl.DbUtil;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryJobDAOImpl;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryLogDAOImpl;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryParamDAOImpl;
import org.walkerljl.retry.demo.defaults.DefaultRetryBroker;
import org.walkerljl.retry.demo.defaults.DefaultRetryService;
import org.walkerljl.retry.demo.service.User;
import org.walkerljl.retry.demo.service.impl.retry.SyncUserInfoRetryHandler;
import org.walkerljl.retry.impl.RetryJobHandlerRepository;
import org.walkerljl.retry.impl.RetryJobLoader;
import org.walkerljl.retry.impl.defaults.DefaultRemoteRetryJobQueue;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobDispatcher;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobLoader;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.util.JSONUtil;
import org.walkerljl.retry.impl.util.RetryIntervalCalculator;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.db.ds.DataSourceFactory;
import org.walkerljl.toolkit.db.ds.impl.dbcp.DbcpDataSourceFactory;
import org.walkerljl.toolkit.db.orm.enums.DatabaseType;
import org.walkerljl.toolkit.db.orm.session.Configuration;

/**
 * RetryServer
 *
 * @author xingxun
 */
public class DefaultRetryServer extends AbstractRetryServer implements RetryServer {

    private Logger                   logger;
    private RetryConfig              retryConfig;
    private RemoteRetryJobQueue      remoteRetryJobQueue;
    private BlockingQueue<RetryJob>  retryJobQueue;
    private RetryService             retryService;
    private RetryJobDispatcher       retryJobDispatcher;
    private RetryJobLoader           retryJobLoader;
    private RetryBroker              retryBroker;
    private ScheduledExecutorService retryJobFetcherScheduler;
    private ScheduledExecutorService retryJobLoaderScheduler;
    private ScheduledExecutorService retryBrokerScheduler;

    @Override
    public void processInit() throws CannotInitResourceException {

        super.processInit();

        //日志设置
        logger = LoggerFactory.getLogger(DefaultRetryServer.class);

        //初始化数据库资源
        ConfiguratorFactory.bind(new PropertiesConfiguratorProvider("orgwalkerljl-db.properties"));
        DataSourceFactory dataSourceFactory = new DbcpDataSourceFactory();
        Configuration configuration = new Configuration();
        configuration.setDatabaseType(DatabaseType.MYSQL);
        configuration.setDataSource(dataSourceFactory.getDataSource());
        DbUtil.getInstance().bindConfiguration(configuration);
        DbUtil.getInstance().init();

        //RetryService 设置
        RetryJobDAO retryJobDAO = new RetryJobDAOImpl();
        RetryLogDAO retryLogDAO = new RetryLogDAOImpl();
        RetryParamDAO retryParamDAO = new RetryParamDAOImpl();
        retryService = new DefaultRetryService(retryJobDAO, retryParamDAO, retryLogDAO);

        //config
        retryConfig = new RetryConfig();
        retryConfig.setJobLoadPageSize(2);
        retryConfig.setMaxJobQuantityPerLoad(5);
        retryJobQueue = new ArrayBlockingQueue<>(100);
        remoteRetryJobQueue = new DefaultRemoteRetryJobQueue(retryJobQueue);

        RetryJobHandlerRepository.register(SyncUserInfoRetryHandler.class.getSimpleName(), new SyncUserInfoRetryHandler());

        retryJobDispatcher = new DefaultRetryJobDispatcher(retryConfig, retryService);
        retryJobDispatcher.start();

        retryJobLoader = new DefaultRetryJobLoader(retryConfig, retryService);
        retryJobLoader.start();

        retryBroker = new DefaultRetryBroker(retryJobDAO, retryParamDAO);
        retryBroker.start();
    }

    @Override
    public String getId() {
        return "01";
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

    @Override
    public RetryJobLoader getRetryJobLoader() {
        return retryJobLoader;
    }

    @Override
    public RetryJobDispatcher getRetryJobDispatcher() {
        return retryJobDispatcher;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

        super.processStart();

        //RetryJobFetcher
        retryJobFetcherScheduler = Executors.newSingleThreadScheduledExecutor();
        retryJobFetcherScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    RetryJob retryJob = retryJobQueue.take();
                    retryJobDispatcher.dispatch(retryJob);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        //RetryJobLoader
        retryJobLoaderScheduler = Executors.newSingleThreadScheduledExecutor();
        retryJobLoaderScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    retryJobLoader.load(remoteRetryJobQueue);
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }, 1, 5, TimeUnit.SECONDS);

        //RetryBroker
        retryBrokerScheduler = Executors.newSingleThreadScheduledExecutor();
        retryBrokerScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    retryBroker.submit(buildRetryJob());
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }, 1, 60, TimeUnit.SECONDS);
    }

}