package me.gking2224.refdatams.db.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.RefDataTestConfiguration;
import me.gking2224.refdatams.model.Building;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"embedded"})
@ContextConfiguration(classes=RefDataTestConfiguration.class)
@Transactional
@Rollback
@Sql({"./LocationRepositoryIT.sql"})
public class BuildingRepositoryIT {

    @Autowired
    protected BuildingRepository repository;
    
    
    @Test
    public void testFindAll() {
        
        List<Building> ll = repository.findAll();
        
        assertNotNull(ll);
        assertTrue(ll.size() > 0);
    }
}
