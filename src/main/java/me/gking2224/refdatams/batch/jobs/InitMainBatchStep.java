package me.gking2224.refdatams.batch.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitMainBatchStep {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(InitMainBatchStep.class);

    @Autowired
    private StepBuilderFactory steps;

    @Bean("initMainBatch")
    protected Step initMainBatch() {
        
        return steps.get("initMainBatch")
                .tasklet(tasklet())
                .allowStartIfComplete(true)
                .build();
    }
    
    protected Tasklet tasklet() {
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
            
        };
    }
}
