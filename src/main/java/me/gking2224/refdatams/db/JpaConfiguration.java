package me.gking2224.refdatams.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import me.gking2224.common.db.EntityManagerFactoryBeanBuilder;

@Configuration
@EnableJpaRepositories(basePackages={"me.gking2224.refdatams"}, entityManagerFactoryRef="entityManagerFactory")
public class JpaConfiguration {
    
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
            @Qualifier("hibernateProperties") Properties hibernateProperties,
            DataSource dataSource,
            JpaVendorAdapter vendorAdapter
    ) {
        return new EntityManagerFactoryBeanBuilder()
                .packages("me.gking2224.refdatams")
                .properties(hibernateProperties)
                .dataSource(dataSource)
                .vendorAdapter(vendorAdapter)
                .build();
    }
}
