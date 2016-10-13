package me.gking2224.refdatams.batch.jobs;

import static me.gking2224.common.utils.NullSafeOperations.getIfNotNull;
import static me.gking2224.common.utils.NullSafeOperations.toStringOrNull;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;
import static org.springframework.util.StringUtils.hasLength;

import java.math.BigDecimal;
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

import me.gking2224.common.batch.generic.AbstractEtlBatchConfiguration;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;
import me.gking2224.refdatams.service.RefDataService;

@Configuration
public class ResourceBatchConfiguration extends AbstractEtlBatchConfiguration<ResourceFileRow, Resource> {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    private RefDataService refDataService;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean("resourceBatch")
    public Flow resourceBatch() {
        return fileProcessFlowBuilder(steps, batchProperties).build();
    }
    
    @Override
    protected Function<String[], ResourceFileRow> fromFields() {
        return (String[] a) -> {
            ResourceFileRow rv = new ResourceFileRow();
            rv.setFirstName(a[0]);
            rv.setSurname(a[1]);
            rv.setContractType(a[2]);
            rv.setRate(a[3]);
            rv.setLocation(a[4]);
            return rv;
        };
    }

    @Override
    protected Function<Resource, Void> writeFunction() {
        return r -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status -> refDataService.saveResource(r));
            return null;
        };
    }
    
    @Override
    protected String getFlowName() {
        return "resource";
    }
    
    @Override
    protected String[] fieldNames() {
        return new String[] {"firstName", "surname", "contractType", "rate", "location"};
    }

    @Override
    protected Function<Resource, ResourceFileRow> toRowObject() {
        return (r) -> {
          ResourceFileRow rf = new ResourceFileRow();
          Location location = r.getLocation();
          Person p = r.getPerson();
          rf.setFirstName(getIfNotNull(p, () ->p.getFirstName()));
          rf.setSurname(getIfNotNull(p, () ->p.getSurname()));
          rf.setContractType(toStringOrNull(r.getContractType()));
          rf.setRate(toStringOrNull(r.getBillRate()));
          rf.setLocation(getIfNotNull(location, ()->location.getName()));
          return rf;
        };
    }

    @Override
    protected Function<ResourceFileRow, Resource> toEntityObject() {
        return (rf) -> {
            Resource r = new Resource();
            Person p = new Person();
            p.setFirstName(rf.getFirstName());
            p.setSurname(rf.getSurname());
            r.setPerson(p);
            ContractType type = new ContractType(rf.getContractType());
            r.setContractType(type);
            String strRate = rf.getRate();
            r.setBillRate(!hasLength(strRate) ? null : new BigDecimal(strRate));
            Location l = new Location();
            l.setName(rf.getLocation());
            r.setLocation(l);
            return r;
          };
    }

    @Override
    protected Class<Resource> getOutClass() {
        return Resource.class;
    }

    @Override
    protected Class<ResourceFileRow> getInClass() {
        return ResourceFileRow.class;
    }

}
