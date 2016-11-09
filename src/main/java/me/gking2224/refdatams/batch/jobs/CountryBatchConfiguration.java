package me.gking2224.refdatams.batch.jobs;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import java.util.Properties;
import java.util.function.Function;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import me.gking2224.common.batch.generic.AbstractEtlBatchConfiguration;
import me.gking2224.refdatams.db.dao.CountryDao;
import me.gking2224.refdatams.model.Country;

@Configuration
public class CountryBatchConfiguration extends AbstractEtlBatchConfiguration<CountryInFile, Country> {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean("countryBatch")
    public Flow countryBatch(ConfigurableEnvironment environment) {
        return fileProcessFlowBuilder(steps, environment, batchProperties).build();
    }
    
    @Override
    protected String getFlowName() {
        return "country";
    }
    
    @Override
    protected String[] fieldNames() {
        return new String[] {"code", "name", "fileField"};
    }

    @Override
    protected Function<String[], CountryInFile> fromFields() {
        return (String[] a) -> {
            CountryInFile rv = new CountryInFile();
            rv.setCode(a[0]);
            rv.setDescription(a[1]);
            rv.setFileField(a[2]);
            return rv;
        };
    }

    @Override
    protected Function<Country, Void> writeFunction() {
        return country -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status -> countryDao.save(country));
            return null;
        };
    }
    
    @Override
    protected Function<CountryInFile, Country> toEntityObject() {
        return (cf) -> {
            Country c = new Country();
            c.setCode(cf.getCode());
            c.setName(cf.getDescription());
            return c;
        };
    }
    
    @Override
    protected Function<Country, CountryInFile> toRowObject() {
        return (Country c) -> {
            CountryInFile cf = new CountryInFile();
            cf.setCode(c.getCode());
            cf.setDescription(c.getName());
            cf.setFileField("error");
            return cf;
        };
    }

    @Override
    protected Class<Country> getOutClass() {
        return Country.class;
    }

    @Override
    protected Class<CountryInFile> getInClass() {
        return CountryInFile.class;
    }

}
