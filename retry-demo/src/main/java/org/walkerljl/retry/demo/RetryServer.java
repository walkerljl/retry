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
import org.walkerljl.retry.RetryJobFetcher;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.daointerface.impl.DbUtil;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryJobDAOImpl;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryLogDAOImpl;
import org.walkerljl.retry.db.dao.daointerface.impl.RetryParamDAOImpl;
import org.walkerljl.retry.demo.impl.defaults.DefaultRetryService;
import org.walkerljl.retry.demo.impl.defaults.DefaultRetryBroker;
import org.walkerljl.retry.demo.test.CreateUserRetryHandler;
import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.exception.resouce.CannotInitResourceException;
import org.walkerljl.retry.impl.defaults.DefaultRemoteRetryJobQueue;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobLoader;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.retry.standard.Machine;
import org.walkerljl.retry.standard.abstracts.AbstractMachine;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobFetcher;
import org.walkerljl.retry.support.RetryJobDispatcher;
import org.walkerljl.retry.support.RetryJobHandlerRepository;
import org.walkerljl.retry.support.RetryJobLoader;
import org.walkerljl.retry.support.impl.DefaultRetryJobDispatcher;
import org.walkerljl.toolkit.db.ds.DataSourceFactory;
import org.walkerljl.toolkit.db.ds.impl.dbcp.DbcpDataSourceFactory;
import org.walkerljl.toolkit.db.orm.enums.DatabaseType;
import org.walkerljl.toolkit.db.orm.session.Configuration;

/**
 *
 * @author xingxun
 */
public class RetryServer extends AbstractMachine implements Machine {

    private Logger                   logger;
    private RetryConfig              retryConfig;
    private RemoteRetryJobQueue      remoteRetryJobQueue;
    private BlockingQueue<RetryJob>  retryJobQueue;
    private RetryService             retryService;
    private RetryJobDispatcher       retryJobDispatcher;
    private RetryJobLoader           retryJobLoader;
    private RetryJobFetcher          retryJobFetcher;
    private RetryBroker              retryBroker;
    private ScheduledExecutorService retryJobFetcherScheduler;
    private ScheduledExecutorService retryJobLoaderScheduler;
    private ScheduledExecutorService retryWaiterScheduler;

    @Override
    public void init() throws CannotInitResourceException {
        //日志设置
        logger = LoggerFactory.getLogger(RetryServer.class);

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
        retryJobQueue = new ArrayBlockingQueue<RetryJob>(1000);
        remoteRetryJobQueue = new DefaultRemoteRetryJobQueue(retryJobQueue);

        RetryJobHandlerRepository.register(CreateUserRetryHandler.class.getSimpleName(), new CreateUserRetryHandler());

        retryJobDispatcher = new DefaultRetryJobDispatcher(retryConfig, retryService);
        retryJobFetcher = new DefaultRetryJobFetcher(retryJobDispatcher);
        retryJobLoader = new DefaultRetryJobLoader(retryConfig, retryService);
        retryBroker = new DefaultRetryBroker(retryJobDAO, retryParamDAO);
    }

    @Override
    public String getId() {
        return "01";
    }

    @Override
    public String getGroup() {
        return "retry";
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        //RetryJobFetcher
        retryJobFetcherScheduler = Executors.newSingleThreadScheduledExecutor();
        retryJobFetcherScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    RetryJob retryJob = retryJobQueue.take();
                    retryJobFetcher.onFetch(retryJob);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        //RetryJobLoader
        retryJobLoader = new DefaultRetryJobLoader(retryConfig, retryService);
        retryJobLoaderScheduler = Executors.newSingleThreadScheduledExecutor();
        retryJobLoaderScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    retryJobLoader.load(remoteRetryJobQueue);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        //RetryBroker
        retryWaiterScheduler = Executors.newSingleThreadScheduledExecutor();
        retryWaiterScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    retryBroker.submit(buildRetryJob());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private RetryJob buildRetryJob() {

        RetryJob retryJob = new RetryJob();
        retryJob.setBizType("hello");
        retryJob.setBizId(UUID.randomUUID().toString());
        retryJob.setPriority(RetryPriorityEnum.LOW);
        retryJob.setTargetIdentifer(CreateUserRetryHandler.class.getSimpleName());
        retryJob.setLastRetryTime(new Date());
        retryJob.setNextRetryTime(retryJob.getLastRetryTime());
        retryJob.setRetryRule("5000");
        retryJob.setAttempts(0);
        retryJob.setMaxAttempts(5);
        retryJob.setStatus(RetryJobStatusEnum.UNPROCESS);
        retryJob.setCreated(new Date());
        retryJob.setCreator("xingxun");
        retryJob.setModified(retryJob.getCreated());
        retryJob.setModifier(retryJob.getCreator());

        List<RetryParam> retryParams = new ArrayList<>(1);
        retryJob.setParams(retryParams);
        RetryParam retryParam = new RetryParam();
        retryParams.add(retryParam);
        retryParam.setValue("{\"id\":\"xingxun\",\"name\":\"行寻\"}");
        retryParam.setStatus(RetryParamStatusEnum.NORMAL);
        retryParam.setCreated(new Date());
        retryParam.setCreator("xingxun");
        retryParam.setModified(retryJob.getCreated());
        retryParam.setModifier(retryJob.getCreator());

        return retryJob;
    }

    @Override
    public void processStop() throws CannotStopMachineException {

        if (!retryJobLoaderScheduler.isShutdown()) {
            retryJobLoaderScheduler.shutdown();
        }
        if (!retryJobFetcherScheduler.isShutdown()) {
            retryJobFetcherScheduler.shutdown();
        }
    }
}