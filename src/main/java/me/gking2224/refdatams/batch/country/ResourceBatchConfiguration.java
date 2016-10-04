package me.gking2224.refdatams.batch.country;

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

import me.gking2224.common.batch.generic.ProcessFileBatchFlowBuilder;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;
import me.gking2224.refdatams.service.RefDataService;

@Configuration
public class ResourceBatchConfiguration  {

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
        return new ProcessFileBatchFlowBuilder<ResourceFileRow, Resource>(
                steps, batchProperties, "resource")
                .writeFunction(writeFunction())
                .fieldNames("firstName", "surname", "contractType", "rate", "location")
                .readFromFileProcessorFunction(toResource())
                .writeToFileProcessorFunction(toRowObject())
                .fieldsMapper((String[] a) -> {
                    ResourceFileRow rv = new ResourceFileRow();
                    rv.setFirstName(a[0]);
                    rv.setSurname(a[1]);
                    rv.setContractType(a[2]);
                    rv.setRate(a[3]);
                    rv.setLocation(a[4]);
                    return rv;
                })
                .fieldExtractor(item -> new String[] {item.getFirstName(), item.getSurname(), item.getContractType(), item.getRate(), item.getLocation()})
                .build();
    }

    private Function<Resource, Void> writeFunction() {
        return r -> {
            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
            tt.execute(status -> refDataService.saveResource(r));
            return null;
        };
    }

    private Function<Resource, ResourceFileRow> toRowObject() {
        return (r) -> {
          ResourceFileRow rf = new ResourceFileRow();
          rf.setFirstName(r.getPerson().getFirstName());
          rf.setSurname(r.getPerson().getSurname());
          rf.setContractType(r.getContractType().toString());
          rf.setRate(r.getBillRate().toString());
          rf.setLocation(r.getLocation().getName());
          return rf;
        };
    }

    private Function<ResourceFileRow, Resource> toResource() {
        return (rf) -> {
            Resource r = new Resource();
            Person p = new Person();
            p.setFirstName(rf.getFirstName());
            p.setSurname(rf.getFirstName());
            r.setPerson(p);
            ContractType type = new ContractType();
            type.setCode(rf.getContractType());
            r.setContractType(type);
            String strRate = rf.getRate();
            r.setBillRate(!hasLength(strRate) ? null : new BigDecimal(strRate));
            Location l = new Location();
            l.setName(rf.getLocation());
            r.setLocation(l);
            return r;
          };
    }

}
