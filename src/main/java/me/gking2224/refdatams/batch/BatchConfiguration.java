package me.gking2224.refdatams.batch;

import static me.gking2224.common.jmx.CommonJmxConfiguration.JMX_MBEAN_GROUP_NAME_PREFIX;
import static me.gking2224.refdatams.batch.ReferenceDataBatch.MAIN_BATCH_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import me.gking2224.common.batch.JobRunResult;
import me.gking2224.common.batch.JobRunner;

@Profile("batch")
@Import({BatchScheduler.class})
@EnableBatchProcessing(modular=true)
@ManagedResource(objectName=BatchConfiguration.SERVICE_NAME_BATCH, description="Batch Service",log=true, logFile="jmx.log", persistPolicy="Never")
public class BatchConfiguration extends DefaultBatchConfigurer {
    public static final String SERVICE_NAME_BATCH = JMX_MBEAN_GROUP_NAME_PREFIX + "Ref Data Batch Configuration";

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired JobRunner jobRunner;
    
    @Autowired PlatformTransactionManager trxManager;
    
    @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.setTaskExecutor(getTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public TaskExecutor getTaskExecutor() {
        SimpleAsyncTaskExecutor te = new SimpleAsyncTaskExecutor();
        return te;
    }

    @Bean
    public TaskScheduler getTaskScheduler() {
        TaskScheduler ts = new ConcurrentTaskScheduler();
        return ts;
    }

    @Bean
    public ApplicationContextFactory countryLoadJobs() {
        return new GenericApplicationContextFactory(ReferenceDataBatch.class);
    }

    @ManagedOperation(description="Run ref data job")
    public String runBatchJob() {
        
        JobRunResult result = jobRunner.runJob(MAIN_BATCH_NAME);
        return result.getStatusMessage();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return trxManager;
    }
}
