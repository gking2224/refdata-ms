package me.gking2224.refdatams.batch.country;

import me.gking2224.common.batch.step.AbstractStep;

//@Configuration
public abstract class MoveFileStep extends AbstractStep {

//    private static Logger logger = LoggerFactory.getLogger(MoveFileStep.class);
//
//    @Autowired
//    private StepBuilderFactory steps;
//    
//    @Bean
//    protected Step moveFileToProcessed(@Qualifier("moveFile") @StepScope Tasklet moveFileTasklet) {
//        
//        return steps.get("moveFileToProcessed").tasklet(moveFileTasklet).listener(this).build();
//    }
//    
//    @Bean("moveFile") @StepScope
//    protected Tasklet moveFile() {
//
//        File f = getFromJobContext(COUNTRIES_CSV_FILE, File.class).orElseThrow(() -> new FatalStepExecutionException("COUNTRIES_CSV_FILE not initialized", null));
//        String suffix = getFromJobContext(PROCESSED_SUFFIX, String.class, "processed");
//        return new MoveFileTaskletBuilder().file(f).addSuffix(suffix).build();
//    }
//
//    @Override
//    protected Logger getLogger() {
//        return logger;
//    }

}
