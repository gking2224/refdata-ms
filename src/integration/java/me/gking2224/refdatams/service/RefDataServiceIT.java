package me.gking2224.refdatams.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.RefDataTestConfigurer;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=RefDataTestConfigurer.class)
@TestPropertySource("/test.properties")
@Transactional()
@EnableJpaRepositories
@Rollback
@ActiveProfiles("embedded")
//@Sql({"LocationRepositoryIT.sql", "ResourceRepositoryIT.sql"})
public class RefDataServiceIT {

    @Autowired
    RefDataService service;
    
    
    @Test
    public void test() {
        String firstName = "Joseph";
        String surname = "Bloggs";
        String contractType = "M";
        String location = "UK";
        
        Resource r = new Resource();
        Location l = new Location();
        l.setName(location);
        ContractType t = new ContractType();
        t.setCode(contractType);
        Person p = new Person();
        p.setFirstName(firstName);
        p.setSurname(surname);
        
        r.setPerson(p);
        r.setLocation(l);
        r.setContractType(t);
       
        
        Resource saved = service.saveResource(r);
        
        assertNotNull(saved);
    }

}
