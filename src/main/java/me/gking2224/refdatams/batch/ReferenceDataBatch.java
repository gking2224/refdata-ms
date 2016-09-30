package me.gking2224.refdatams.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import me.gking2224.refdatams.batch.country.CountryLoadBatchConfiguration;

@Configuration
@ComponentScan("me.gking2224.refdatams.batch.country")
@Import({InitMainBatchStep.class, CountryLoadBatchConfiguration.class})
public class ReferenceDataBatch {
    
    @Autowired
    private JobBuilderFactory jobs;

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ReferenceDataBatch.class);
    
    @Bean("mainRefDataBatch")
    public Job mainBatch(
            @Qualifier("initMainBatch") Step initBatch,
            @Qualifier("countryBatch") Flow countryBatch) {
//            @Qualifier("resourceBatch") Flow resourceFlow) {
        
        return jobs.get("mainRefDataBatch")
            .start(initBatch)
            .split(new SimpleAsyncTaskExecutor("refDataBatch"))
                .add(countryBatch)//, resourceFlow)
            .end()
            .build();
    }
}
