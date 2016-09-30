package me.gking2224.refdatams.batch.country;

import static me.gking2224.refdatams.batch.country.CountryBatchConstants.CSV_FILE;

import java.io.File;
import java.util.Properties;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.builder.AbstractTaskletStepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.BackOffPolicy;

import me.gking2224.common.batch.step.AbstractEtlStep;
import me.gking2224.common.batch.step.FlatFileItemReaderBuilder;
import me.gking2224.common.batch.step.FunctionLineWriter;
import me.gking2224.refdatams.db.dao.CountryDao;
import me.gking2224.refdatams.model.Country;

@Configuration
public class ProcessCountryCsvStep
extends AbstractEtlStep<Country,Country> {
    
    private static Logger logger = LoggerFactory.getLogger(ProcessCountryCsvStep.class);
    
    protected Logger getLogger() { return logger; }
    
    @Autowired
    private CountryDao countryDao;

    @Autowired @Qualifier("countryBatchProperties")
    private Properties countryBatchProperties;
    
    @Override
    protected Properties getJobProperties() {
        return countryBatchProperties;
    }
    
    @Override
    protected String getStepName() {
        return "processCsv";
    }

    @Bean("processCountryCsvFile")
    protected Step processCountryCsvFile(
            @SuppressWarnings("rawtypes") @Qualifier("countryCsvReader") FlatFileItemReader countryCsvReader,
            @SuppressWarnings("rawtypes") @Qualifier("countryWriter") ItemWriter writer,
            @Qualifier("defaultBackOffPolicy") BackOffPolicy backOffPolicy) {
        
        @SuppressWarnings("unchecked")
        SimpleStepBuilder<Country,Country> builder = steps.get("processCountryCsvFile")
                .listener(this)
                .<Country, Country> chunk(5)
                .writer(writer)
                .reader(countryCsvReader);
        
        AbstractTaskletStepBuilder<SimpleStepBuilder<Country, Country>> builder2 =
                getFaultToleranceConfigurer()
                .configure(builder)
                .allowStartIfComplete(true)
                .listener((RetryListener)this);
        
        TaskletStep step = getStepConfigurer().configure(builder2).build();
        return step;
    }
    
    @SuppressWarnings("rawtypes")
    @Bean("countryCsvReader")
    @StepScope
    protected FlatFileItemReader countryCsvReader(
            LineMapper<Country> countryCsvLineMapper) {
        
        return new FlatFileItemReaderBuilder<Country>(getProperties())
                .name("countryCsvReader")
                .lineMapper(countryCsvLineMapper)
                .skippedLinesCallback(this)
                .file(getFromJobContext(CSV_FILE, File.class).orElseThrow(notInitialized(CSV_FILE)))
                .build();
    }
    
    @Bean
    protected FunctionLineWriter<Country> countryWriter() {
        return new FunctionLineWriter<Country>(writeFunc());
    }

    private Function<Country, Void> writeFunc() {
        return (Country c) -> {
            countryDao.batchSaveOrUpdate(c);
            return null;
        };
        
    }

    @Override
    protected <T, E extends Throwable> boolean doOpen(RetryContext context, RetryCallback<T, E> callback) {
        int retryCount = context.getRetryCount();
        return retryCount < 20; // TODO change this
    }

}
