package me.gking2224.refdatams.batch;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.step.AbstractStep;

@Configuration
public class InitMainBatchStep extends AbstractStep implements StepExecutionListener, ExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(InitMainBatchStep.class);

    @Autowired
    private StepBuilderFactory steps;

    @Autowired @Qualifier("countryBatchProperties")
    private Properties countryBatchProperties;

    @Bean("initMainBatch")
    protected Step initMainBatch(@StepScope @Qualifier("initMainBatchTasklet") Tasklet initMainBatch) {
        
        return steps.get("initMainBatch")
                .tasklet(initMainBatch)
                .listener(this)
                .exceptionHandler(this)
                .allowStartIfComplete(true)
                .build();
    }
    
    @Bean("initMainBatchTasklet") @StepScope
    protected Tasklet initMainBatchTasklet() {
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
            
        };
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected String getStepName() {
        return "initMainBatch";
    }

}
