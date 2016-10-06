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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import me.gking2224.common.batch.generic.AbstractBatchConfiguration;
import me.gking2224.refdatams.model.Building;
import me.gking2224.refdatams.model.City;
import me.gking2224.refdatams.model.Country;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.service.RefDataService;

@Configuration
public class LocationBatchConfiguration extends AbstractBatchConfiguration<LocationFileRow, Location> {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    private RefDataService refDataService;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean("locationBatch")
    public Flow locationBatch() {
        return fileProcessFlowBuilder(steps, batchProperties).build();
    }
    
    @Override
    protected String[] fieldNames() {
        return new String[] {"name", "building", "city", "country"};
    }
    
    @Override
    protected Function<Location, Void> writeFunction() {
        return l -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status ->refDataService.saveLocation(l));
            return null;
        };
    }
    
    @Override
    protected String getFlowName() {
        return "location";
    }

    @Override
    protected Function<String[], LocationFileRow> fromFields() {
        return (s) -> new LocationFileRow(s);
    }

    @Override
    protected Function<Location, LocationFileRow> toRowObject() {
        return (r) -> {
            LocationFileRow lf = new LocationFileRow();
            lf.setName(r.getName());
            lf.setBuilding(r.getBuildingName());
            lf.setCity(r.getCityName());
            lf.setCountry(r.getCountryCode());
            return lf;
          };
    }
    
    @Override
    protected Function<LocationFileRow, Location> toEntityObject() {
        return (lf) -> {
            Location l = new Location();
            l.setBuilding(createBuilding(lf));
            l.setCity(createCity(lf));
            l.setCountry(createCountry(lf));
            l.setName(lf.getName());
            
            return l;
          };
    }

    private Country createCountry(LocationFileRow lf) {
        return new Country(lf.getCountry());
    }

    private City createCity(LocationFileRow lf) {
        String city = lf.getCity();
        return city == null ? null : new City(city, createCountry(lf));
    }

    private Building createBuilding(LocationFileRow lf) {
        return new Building(lf.getBuilding(), createCity(lf));
    }
    
    @Override
    protected Class<Location> getOutClass() {
        return Location.class;
    }
    
    @Override
    protected Class<LocationFileRow> getInClass() {
        return LocationFileRow.class;
    }
}
