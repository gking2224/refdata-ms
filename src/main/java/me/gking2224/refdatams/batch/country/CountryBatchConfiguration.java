package me.gking2224.refdatams.batch.country;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import java.util.Properties;
import java.util.function.Function;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import me.gking2224.common.batch.generic.ProcessFileBatchFlowBuilder;
import me.gking2224.refdatams.db.dao.CountryDao;
import me.gking2224.refdatams.model.Country;

@Configuration
public class CountryBatchConfiguration  {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean("countryBatch")
    public Flow countryBatch() {
        return new ProcessFileBatchFlowBuilder<CountryInFile, Country>(
                steps, batchProperties, "country")
                .writeFunction(writeFunction())
                .fieldNames("code", "name", "fileField")
                
                .fieldsMapper((String[] a) -> {
                    CountryInFile rv = new CountryInFile();
                    rv.setCode(a[0]);
                    rv.setDescription(a[1]);
                    rv.setFileField(a[2]);
                    return rv;
                })
                .readFromFileProcessorFunction(toCountry())
                .writeToFileProcessorFunction(fromCountry())
                .fieldExtractor(item -> new String[] {item.getCode(), item.getDescription(), item.getFileField()})
//                .fieldExtractor(fieldExtractor)
                .build();
    }

    private Function<Country, Void> writeFunction() {
        return country -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status -> countryDao.save(country));
            return null;
        };
    }
    
    private Function<CountryInFile, Country> toCountry() {
        return (cf) -> {
            Country c = new Country();
            c.setCode(cf.getCode());
            c.setName(cf.getDescription());
            return c;
        };
    }
    
    private Function<Country, CountryInFile> fromCountry() {
        return (c) -> {
            CountryInFile cf = new CountryInFile();
            cf.setCode(c.getCode());
            cf.setDescription(c.getName());
            cf.setFileField("error");
            return cf;
        };
    }

}
