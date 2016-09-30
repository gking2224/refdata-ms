package me.gking2224.refdatams.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@Profile("batch")
@Import(BatchScheduler.class)
@EnableBatchProcessing(modular=true)
public class BatchConfiguration extends DefaultBatchConfigurer {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
    
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
    public ApplicationContextFactory countryLoadJobs() {
        return new GenericApplicationContextFactory(ReferenceDataBatch.class);
    }
}
