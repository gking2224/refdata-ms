package me.gking2224.refdatams.batch.country;

import me.gking2224.common.batch.step.AbstractEtlStep;
import me.gking2224.refdatams.model.Country;

//@Configuration
public abstract class ProcessCountryCsvStep
extends AbstractEtlStep<Country,Country> {
    
//    private static Logger logger = LoggerFactory.getLogger(ProcessCountryCsvStep.class);
//    
//    protected Logger getLogger() { return logger; }
//    
//    @Autowired
//    private CountryDao countryDao;
//
//    @Autowired @Qualifier("countryBatchProperties")
//    private Properties countryBatchProperties;
//    
//    @Override
//    protected Properties getJobProperties() {
//        return countryBatchProperties;
//    }
//    
//    @Override
//    protected String getStepName() {
//        return "processCountryCsv";
//    }
//
//    @Bean("processCountryCsvFile")
//    protected Step processCountryCsvFile(
//            @SuppressWarnings("rawtypes") @Qualifier("countryCsvReader") FlatFileItemReader countryCsvReader,
//            @SuppressWarnings("rawtypes") @Qualifier("countryWriter") ItemWriter writer,
//            @Qualifier("defaultBackOffPolicy") BackOffPolicy backOffPolicy) {
//        
//        @SuppressWarnings("unchecked")
//        SimpleStepBuilder<Country,Country> builder = steps.get("processCountryCsvFile")
//                .listener(this)
//                .<Country, Country> chunk(5)
//                .writer(writer)
//                .reader(countryCsvReader);
//        
//        AbstractTaskletStepBuilder<SimpleStepBuilder<Country, Country>> builder2 =
//                getFaultToleranceConfigurer()
//                .configure(builder)
//                .allowStartIfComplete(true)
//                .listener((RetryListener)this);
//        
//        TaskletStep step = getStepConfigurer().configure(builder2).build();
//        return step;
//    }
//    
//    @SuppressWarnings("rawtypes")
//    @Bean("countryCsvReader")
//    @StepScope
//    protected FlatFileItemReader countryCsvReader(
//            LineMapper<Country> countryCsvLineMapper) {
//        
//        return new FlatFileItemReaderBuilder<Country>(getProperties())
//                .name("countryCsvReader")
//                .lineMapper(countryCsvLineMapper)
//                .skippedLinesCallback(this)
//                .file(getFromJobContext(COUNTRIES_CSV_FILE, File.class).orElseThrow(notInitialized(COUNTRIES_CSV_FILE)))
//                .build();
//    }
//    
//    @Bean
//    protected FunctionLineWriter<Country> countryWriter() {
//        return new FunctionLineWriter<Country>(writeFunc());
//    }
//
//    private Function<Country, Void> writeFunc() {
//        return (Country c) -> {
//            countryDao.batchSaveOrUpdate(c);
//            return null;
//        };
//        
//    }
//    
//    @Override
//    protected void doBeforeEtlStep() {
//        putInJobContext(COUNTRIES_SKIPPED_ITEMS_LIST, new ArrayList<Country>());
//    }
//    
//    @Override
//    protected void doOnSkipInWrite(Country item, Throwable t) {
//        @SuppressWarnings("unchecked")
//        List<Country> failures = getFromJobContext(COUNTRIES_SKIPPED_ITEMS_LIST, List.class).orElseThrow(
//                () -> new FatalStepExecutionException("COUNTRIES_SKIPPED_ITEMS_LIST not initialized", null));
//        failures.add(item);
//    }
//
//    @Override
//    protected <T, E extends Throwable> boolean doOpen(RetryContext context, RetryCallback<T, E> callback) {
//        int retryCount = context.getRetryCount();
//        return retryCount < 20; // TODO change this
//    }

}
