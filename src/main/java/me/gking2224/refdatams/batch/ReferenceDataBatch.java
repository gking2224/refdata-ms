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

import me.gking2224.refdatams.batch.country.CountryBatchConfiguration;
import me.gking2224.refdatams.batch.country.ResourceBatchConfiguration;

@Configuration
@ComponentScan("me.gking2224.refdatams.batch.country")
@Import({InitMainBatchStep.class, CountryBatchConfiguration.class, ResourceBatchConfiguration.class})
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
            @Qualifier("resourceBatch") Flow resourceBatch
    ) {
        SimpleJobBuilder builder = jobs.get("mainRefDataBatch")
            .start(initBatch);
        if (async)
            async(builder, countryBatch, resourceBatch);
        else
            sequential(builder, countryBatch, resourceBatch);
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
