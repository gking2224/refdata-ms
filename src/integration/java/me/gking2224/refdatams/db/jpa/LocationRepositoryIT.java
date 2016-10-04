package me.gking2224.refdatams.db.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.mvc.RefDataWebAppTestConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes=RefDataWebAppTestConfigurer.class)
@TestPropertySource("/test.properties")
@Transactional()
@EnableJpaRepositories
@Rollback
@ActiveProfiles("embedded")
@Sql({"LocationRepositoryIT.sql", "ResourceRepositoryIT.sql"})
public class LocationRepositoryIT {

    @Autowired
    protected LocationRepository repository;
    
    
    @Test
    public void testFindAll() {
        
        List<Location> ll = repository.findAll();
        
        assertNotNull(ll);
        assertTrue(ll.size() > 0);
        
        
        Map<String, BigDecimal> rates = ll.get(0).getLocationRatesMap();
        assertNotNull(rates);
        
        System.out.println(rates);
    }
}
