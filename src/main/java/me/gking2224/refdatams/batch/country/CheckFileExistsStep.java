package me.gking2224.refdatams.batch.country;

import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.repeat.exception.ExceptionHandler;

import me.gking2224.common.batch.step.AbstractStep;

//@Configuration
public abstract class CheckFileExistsStep extends AbstractStep implements StepExecutionListener, ExceptionHandler {

//    private static Logger logger = LoggerFactory.getLogger(CheckFileExistsStep.class);
//
//    @Autowired
//    private StepBuilderFactory steps;
//
//    @Autowired @Qualifier("countryBatchProperties")
//    private Properties countryBatchProperties;
//
//    @Bean("checkForCountryCsvFile")
//    protected Step checkForCountryCsvFile(@StepScope Tasklet checkExistsTasklet) {
//        
//        return steps.get("checkForCountryCsvFile")
//                .tasklet(checkExistsTasklet)
//                .listener(this)
//                .exceptionHandler(this)
//                .allowStartIfComplete(true)
//                .build();
//    }
//    
//    @Override
//    protected ExitStatus doAfterStep(StepExecution stepExecution) {
//        if (getStepExecution().getFailureExceptions().isEmpty()) return ExitStatus.COMPLETED;
//        return ExitStatus.FAILED;
//    }
//
//    @Override
//    protected void doHandleException(RepeatContext context, Throwable throwable) throws Throwable {
//        context.setTerminateOnly();
//    }
//    
//    @Bean("checkExistsTasklet") @StepScope
//    protected Tasklet checkExistsTasklet(@Qualifier("countryBatchProperties") Properties props) {
//        File f = getFromJobContext(COUNTRIES_CSV_FILE, File.class).orElseThrow(notInitialized(COUNTRIES_CSV_FILE));
//        return new CheckForFileTaskletBuilder().file(f).properties(props).build();
//    }
//
//    @Override
//    protected Logger getLogger() {
//        return logger;
//    }
//
//    @Override
//    protected Properties getJobProperties() {
//        return countryBatchProperties;
//    }
//
//    @Override
//    protected String getStepName() {
//        return "checkCsvFileExists";
//    }

}
