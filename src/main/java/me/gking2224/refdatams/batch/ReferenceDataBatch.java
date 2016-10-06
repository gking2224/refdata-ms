package me.gking2224.refdatams.batch;


import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import me.gking2224.refdatams.batch.jobs.CountryBatchConfiguration;
import me.gking2224.refdatams.batch.jobs.LocationBatchConfiguration;
import me.gking2224.refdatams.batch.jobs.LocationRatesBatchConfiguration;
import me.gking2224.refdatams.batch.jobs.ResourceBatchConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.refdatams.batch.jobs"})
@Import({
    InitMainBatchStep.class,
    CountryBatchConfiguration.class,
    ResourceBatchConfiguration.class,
    LocationBatchConfiguration.class,
    LocationRatesBatchConfiguration.class
})
public class ReferenceDataBatch {
    
    private boolean async = true;
    
    @Autowired
    private JobBuilderFactory jobs;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ReferenceDataBatch.class);
    
    @Bean("mainRefDataBatch")
    public Job mainBatch(
            @Qualifier("initMainBatch") Step initBatch,
            @Qualifier("countryBatch") Flow countryBatch,
            @Qualifier("resourceBatch") Flow resourceBatch,
            @Qualifier("locationBatch") Flow locationBatch,
            @Qualifier("locationRatesBatch") Flow locationRatesBatch
    ) {
        SimpleJobBuilder builder = jobs.get("mainRefDataBatch")
            .start(initBatch);
        
        Flow[] flows = new Flow[] { countryBatch, locationBatch, locationRatesBatch, resourceBatch };
        if (async)
            async(builder, flows);
        else
            sequential(builder, flows);
        return builder.build();
    }

    private void async(final SimpleJobBuilder builder, final Flow... flows) {


        builder.split(new SimpleAsyncTaskExecutor())
            .add(flows);
    }

    private void sequential(final SimpleJobBuilder builder, final Flow... flows) {
        
        Arrays.asList(flows).forEach(f -> builder.next(flowStep(f)));
    }

    private Step flowStep(Flow f) {
        return steps.get(f.getName()+"Flow").flow(f).build();
    }
}
