package me.gking2224.refdatams.batch;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import me.gking2224.common.batch.JobParametersBuilderBuilder;

@Configuration
@Profile("batch")
@EnableAsync
@EnableScheduling
public class BatchScheduler {
    
    private static Logger logger = LoggerFactory.getLogger(BatchScheduler.class);
    
    @Autowired JobRegistry jobRegistry;
    
    @Autowired @Qualifier("defaultJobParameters")
    private JobParametersBuilderBuilder defaultJobParameters;
    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Scheduled(initialDelay=5000, fixedDelay=120000)
    public void loadCountries() throws NoSuchJobException {
        
        try {
            Job loadCountriesJob = jobRegistry.getJob("mainRefDataBatch");
            
            JobParametersBuilder jobParametersBuilder = defaultJobParameters.getJobParametersBuilder();
            
            JobExecution execution = jobLauncher.run(loadCountriesJob, jobParametersBuilder.toJobParameters());
            logger.debug("Launched loadCountries job: {}", execution);
        }
        catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            logger.error("Country Load scheduled job failed: {} ", ExceptionUtils.getRootCause(e).getMessage());
        }
    }
    
    @EventListener(ContextStartedEvent.class)
    public void contextStartedEvent(ContextStartedEvent event) {
        logger.debug("Batch started");
        try {
            loadCountries();
        } catch (NoSuchJobException e) {
            logger.error("Failed to trigger batch on startup: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }
    
}
