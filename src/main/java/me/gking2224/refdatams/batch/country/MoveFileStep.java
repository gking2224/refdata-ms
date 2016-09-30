package me.gking2224.refdatams.batch.country;

import static me.gking2224.common.batch.BatchConstants.PROCESSED_SUFFIX;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.FatalStepExecutionException;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.step.AbstractStep;
import me.gking2224.common.batch.step.MoveFileTaskletBuilder;

@Configuration
public class MoveFileStep extends AbstractStep {

    private static Logger logger = LoggerFactory.getLogger(MoveFileStep.class);

    @Autowired
    private StepBuilderFactory steps;
    
    @Bean
    protected Step moveFileToProcessed(@Qualifier("moveFile") @StepScope Tasklet moveFileTasklet) {
        
        return steps.get("moveFileToProcessed").tasklet(moveFileTasklet).listener(this).build();
    }
    
    @Bean("moveFile") @StepScope
    protected Tasklet moveFile() {

        File f = getFromJobContext(CountryBatchConstants.CSV_FILE, File.class).orElseThrow(() -> new FatalStepExecutionException("CSV_FILE not initialized", null));
        String suffix = getFromJobContext(PROCESSED_SUFFIX, String.class, "processed");
        return new MoveFileTaskletBuilder().file(f).addSuffix(suffix).build();
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

}
