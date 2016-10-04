package me.gking2224.refdatams.batch.country;

import me.gking2224.common.batch.step.AbstractEtlStep;
import me.gking2224.refdatams.model.Country;

//@Configuration
public abstract class WriteBadRecordsStep extends AbstractEtlStep<Country, Country> {
    
//    private static Logger logger = LoggerFactory.getLogger(WriteBadRecordsStep.class);
//
//    @Override
//    protected Logger getLogger() {
//        return logger;
//    }
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
//        return "writeBadRecords";
//    }
//    
//    @Bean
//    protected Step writeBadRecords(
//            @SuppressWarnings("rawtypes") @Qualifier("countryCsvWriter")  FlatFileItemWriter writer,
//            @SuppressWarnings("rawtypes") @Qualifier("badRecordsReader") ItemReader reader) {
//        @SuppressWarnings("unchecked")
//        SimpleStepBuilder<Country, Country> builder = steps.get("writeBadRecords")
//                        .listener(this)
//                        .<Country, Country> chunk(5)
//                        .writer(writer)
//                        .reader(reader);
//        
//        TaskletStep step = getStepConfigurer().configure(builder).build();
//        return step;
//    }
//
//    @Bean
//    @StepScope
//    protected FlatFileItemWriter<Country> countryCsvWriter(
//            LineAggregator<Country> lineAggregator) throws IOException {
//        FlatFileItemWriter<Country> fr = new FlatFileItemWriter<Country>();
//
//        
//        fr.setName("countryCsvWriter");
//        File f = getFromJobContext(COUNTRIES_BAD_FILE, File.class).orElseThrow(notInitialized(COUNTRIES_BAD_FILE));
//        FileSystemResource resource = new FileSystemResource(f);
//        boolean fileExists = resource.exists();
//        logger.debug("Writing failures to {}", f.getAbsolutePath());
//        if (!fileExists) f.createNewFile();
//        fr.setResource(resource);
//        fr.setLineAggregator(lineAggregator);
//        return fr;
//    }
//    
//    @Bean public LineAggregator<Country> getCountryCsvLineAggregator() {
//        
//        DelimitedLineAggregator<Country> dla = new DelimitedLineAggregator<Country>();
//        dla.setFieldExtractor(item -> new String[] {item.getCode(), item.getName()});
//        return dla;
//    }
//    
//    @Bean("badRecordsReader")
//    @StepScope
//    protected ItemReader<Country> badRecordsReader() {
//        @SuppressWarnings("unchecked")
//        List<Country> skippedItems = getFromJobContext(COUNTRIES_SKIPPED_ITEMS_LIST, List.class).orElseThrow(notInitialized(SKIPPED_ITEMS_LIST));
//        return new ListItemReader<Country>("countryBadRecordsReader", skippedItems);
//    }

}
