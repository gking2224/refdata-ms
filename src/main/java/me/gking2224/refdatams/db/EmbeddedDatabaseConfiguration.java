package me.gking2224.refdatams.db;

import static me.gking2224.common.utils.SpringConfigurationUtils.getInitProperty;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import me.gking2224.common.db.DatabaseConfiguration;
import me.gking2224.common.db.embedded.EmbeddedMySQLDatabase;
import me.gking2224.common.db.embedded.EmbeddedMySQLDatabaseBuilder;

@Configuration
@Profile("embedded")
@ComponentScan({"me.gking2224.refdatams.db"})
public class EmbeddedDatabaseConfiguration {
    
    @Bean(name="dataSourceProperties")
    public Properties getDataSourceProperties() throws IOException {
        Properties dsProps = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/datasource.properties"));
        dsProps.setProperty(DatabaseConfiguration.USERNAME_PROPERTY, "root");
        dsProps.setProperty(DatabaseConfiguration.PASSWORD_PROPERTY, "");
        return dsProps;
    }
    
    @Bean(name="dataSource")
    public EmbeddedMySQLDatabase getDataSource(
            @Qualifier("dataSourceProperties") Properties dataSourceProperties) {

        EmbeddedMySQLDatabaseBuilder builder = new EmbeddedMySQLDatabaseBuilder()
            .setPort(Integer.parseInt(getInitProperty(dataSourceProperties, DatabaseConfiguration.PORT_PROPERTY)))
            .addScript("/db/01_model.sql")
            .setDatabaseName(getInitProperty(dataSourceProperties, DatabaseConfiguration.DATABASE_NAME_PROPERTY));
        
        return builder.build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt;
    }
    
    @Bean(name="transactionManager")
    public PlatformTransactionManager getTransactionManager(
            EntityManagerFactory emf,
            DataSource dataSource,
            JpaDialect jpaDialect
    ) {
        JpaTransactionManager jpatm = new JpaTransactionManager();
        jpatm.setEntityManagerFactory(emf);
        jpatm.setDataSource(dataSource);
        jpatm.setJpaDialect(jpaDialect);
        return jpatm;
    }

    @Bean
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager ptm) {
        TransactionTemplate tt = new TransactionTemplate();
        tt.setTransactionManager(ptm);
        return tt;
    }
}
