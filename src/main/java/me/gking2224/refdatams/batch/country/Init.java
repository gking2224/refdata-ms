package me.gking2224.refdatams.batch.country;

import me.gking2224.common.batch.step.AbstractStep;

//@Configuration
public abstract class Init extends AbstractStep {
    
//    @Value("${batch.baseDir}")
//    private String baseDir;
//
//    private static Logger logger = LoggerFactory.getLogger(Init.class);
//
//    @Autowired
//    private StepBuilderFactory steps;
//    
//    @Bean("initCountryBatch")
//    protected Step initCountryBatch(@Qualifier("setContextParams") @StepScope Tasklet setContextParams) {
//        
//        return steps.get("initCountryBatch").tasklet(setContextParams).listener(this).build();
//    }
//    
//    @Bean("setContextParams") @StepScope
//    protected Tasklet setContextParams() {
//        Map<String, Object> params = new HashMap<String, Object>();
//        String filesDir = String.format(COUNTRIES_FILES_DIR, baseDir);
//        String inDir = filesDir+File.separator+"in";
//        String outDir = filesDir+File.separator+"out";
//        String filename = format("/countries-%s.csv", getBatchDate());
//        
//        params.put(COUNTRIES_CSV_FILE_PATTERN, "countries-{batchDate}.csv");
//        params.put(COUNTRIES_CSV_FILE, new File(inDir, filename));
//        params.put(COUNTRIES_BAD_FILE, new File(inDir, filename+".bad"));
//        params.put(COUNTRIES_IN_DIR, inDir);
//        params.put(COUNTRIES_OUT_DIR, outDir);
//
//        return new InitExecutionContextTaskletBuilder().context(getJobExecution().getExecutionContext()).params(params).build();
//    }
//
//    @Override
//    protected Logger getLogger() {
//        return logger;
//    }

}
