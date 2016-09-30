package me.gking2224.refdatams.batch.country;

import static me.gking2224.refdatams.batch.country.CountryBatchConstants.CSV_FILE;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.step.AbstractStep;
import me.gking2224.common.batch.step.CheckForFileTaskletBuilder;

@Configuration
public class CheckFileExistsStep extends AbstractStep implements StepExecutionListener, ExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CheckFileExistsStep.class);

    @Autowired
    private StepBuilderFactory steps;

    @Autowired @Qualifier("countryBatchProperties")
    private Properties countryBatchProperties;

    @Bean("checkForCountryCsvFile")
    protected Step checkForCountryCsvFile(@StepScope Tasklet checkExistsTasklet) {
        
        return steps.get("checkForCountryCsvFile")
                .tasklet(checkExistsTasklet)
                .listener(this)
                .exceptionHandler(this)
                .allowStartIfComplete(true)
                .build();
    }
    
    @Override
    protected ExitStatus doAfterStep(StepExecution stepExecution) {
        if (getStepExecution().getFailureExceptions().isEmpty()) return ExitStatus.COMPLETED;
        return ExitStatus.FAILED;
    }

    @Override
    protected void doHandleException(RepeatContext context, Throwable throwable) throws Throwable {
        context.setTerminateOnly();
    }
    
    @Bean("checkExistsTasklet") @StepScope
    protected Tasklet checkExistsTasklet(@Qualifier("countryBatchProperties") Properties props) {
        File f = getFromJobContext(CSV_FILE, File.class).orElseThrow(notInitialized(CSV_FILE));
        return new CheckForFileTaskletBuilder().file(f).properties(props).build();
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected Properties getJobProperties() {
        return countryBatchProperties;
    }

    @Override
    protected String getStepName() {
        return "checkCsvFileExists";
    }

}
