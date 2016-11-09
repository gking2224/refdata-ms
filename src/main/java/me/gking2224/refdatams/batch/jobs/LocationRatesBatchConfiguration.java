package me.gking2224.refdatams.batch.jobs;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import java.math.BigDecimal;
import java.util.Arrays;
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
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.service.RefDataService;

@Configuration
public class LocationRatesBatchConfiguration extends AbstractEtlBatchConfiguration<LocationRatesFileRow, Location> {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    private RefDataService refDataService;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean("locationRatesBatch")
    public Flow locationRatesBatch(ConfigurableEnvironment environment) {
        return fileProcessFlowBuilder(steps, environment, batchProperties).build();
    }
    
    @Override
    protected String[] fieldNames() {
        return new String[] {"location", "P", "M", "C"};
    }
    
    @Override
    protected Function<Location, Void> writeFunction() {
        return l -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status -> refDataService.updateLocationWithRates(l));
            return null;
        };
    }
    
    @Override
    protected String getFlowName() {
        return "locationRates";
    }

    @Override
    protected Function<String[], LocationRatesFileRow> fromFields() {
        return (s) -> new LocationRatesFileRow(s);
    }

    @Override
    protected Function<Location, LocationRatesFileRow> toRowObject() {
        return (r) -> {
            LocationRatesFileRow lf = new LocationRatesFileRow();
            lf.setLocation(r.getName());
            lf.setP(toString(r.getLocationRatesMap().get("P")));
            lf.setM(toString(r.getLocationRatesMap().get("M")));
            lf.setC(toString(r.getLocationRatesMap().get("C")));
            return lf;
          };
    }
    
    private String toString(BigDecimal n) {
        return (n == null) ? null : n.toString();
    }

    @Override
    protected Function<LocationRatesFileRow, Location> toEntityObject() {
        return (lf) -> {
            Location l = new Location();
            l.setName(lf.getLocation());
            Arrays.asList(new String[][] {
                { "P", lf.getP() },
                { "M", lf.getM() },
                { "C", lf.getC() }
            }).forEach( a ->
                l.setLocationRate(new ContractType(a[0]), new BigDecimal(a[1])));
            return l;
          };
    }
    
    @Override
    protected Class<Location> getOutClass() {
        return Location.class;
    }
    
    @Override
    protected Class<LocationRatesFileRow> getInClass() {
        return LocationRatesFileRow.class;
    }
}
