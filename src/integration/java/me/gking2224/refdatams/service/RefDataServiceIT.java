package me.gking2224.refdatams.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.RefDataServiceTestInitializer;
import me.gking2224.refdatams.RefDataTestConfiguration;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name="refdatams", classes=RefDataTestConfiguration.class, initializers={RefDataServiceTestInitializer.class})
@Transactional
@Rollback
@Sql({"../db/jpa/LocationRepositoryIT.sql"})
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
    
    @Test
    public void test2() {
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
