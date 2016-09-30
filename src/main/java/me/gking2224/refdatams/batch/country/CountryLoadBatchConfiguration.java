package me.gking2224.refdatams.batch.country;

import static me.gking2224.common.batch.BatchConstants.COMPLETED_WITH_SKIPS;
import static org.springframework.batch.core.ExitStatus.FAILED;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.BatchConstants;
import me.gking2224.common.utils.NestedProperties;

@Configuration
@ComponentScan("me.gking2224.refdatams.batch.country")
public class CountryLoadBatchConfiguration implements JobExecutionListener {

    private static Logger logger = LoggerFactory.getLogger(CountryLoadBatchConfiguration.class);
    
    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    private ThreadLocal<ExecutionContext> executionContext = new ThreadLocal<ExecutionContext>();
    private ThreadLocal<JobExecution> jobExecution = new ThreadLocal<JobExecution>();
    
    @Bean("countryBatch")
    public Flow countryBatch(
            @Qualifier("initCountryBatch") Step init,
            @Qualifier("checkForCountryCsvFile") Step checkExists,
            @Qualifier("processCountryCsvFile") Step process,
            @Qualifier("writeBadRecords") Step writeBadRecords,
            @Qualifier("moveFileToProcessed") Step markProcessed,
            @Qualifier("flowDecider") JobExecutionDecider decider) {
        
        Flow skippedFilesFlow = new FlowBuilder<Flow>("skippedFilesFlow").start(writeBadRecords).next(markProcessed).end();
        
        return new FlowBuilder<Flow>("countryBatch")
                .start(init)
                .next(checkExists)
                .next(process)
                .next(decider)
                    .on(COMPLETED_WITH_SKIPS).to(skippedFilesFlow)
                .from(decider)
                    .on(BatchConstants.COMPLETED).to(markProcessed)
                .end();
    }

    @Bean("flowDecider")
    protected JobExecutionDecider flowDecider() { 
        return new JobExecutionDecider() {
            
            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
                ExitStatus exitStatus = stepExecution.getExitStatus();
                
                if (stepExecution.getWriteSkipCount() > 0) {
                    return new FlowExecutionStatus(COMPLETED_WITH_SKIPS);
                }
                else if (FAILED.equals(exitStatus)){
                    return FlowExecutionStatus.FAILED;
                }
                else {
                    return FlowExecutionStatus.COMPLETED;
                }
            }
        };
    }
    
    @Bean("countryBatchProperties")
    public Properties countryBatchProperties() {
        return new NestedProperties("country", batchProperties);
    }
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.debug("before job");
        this.executionContext.set(jobExecution.getExecutionContext());
        this.jobExecution.set(jobExecution);
    }
    
    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.debug("after job");
    }

}
