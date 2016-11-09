package me.gking2224.refdatams;


import org.apache.commons.cli.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.gking2224.common.AbstractMicroServiceApplication;
import me.gking2224.common.CommonConfiguration;
import me.gking2224.refdatams.aop.AopConfiguration;
import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;
import me.gking2224.refdatams.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.refdatams.service", "me.gking2224.refdatams.model"})
@Import({
    CommonConfiguration.class,
    DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class,
    BatchConfiguration.class,
    WebAppConfiguration.class,
    AopConfiguration.class,
})
public class RefDataMicroServiceApplication extends AbstractMicroServiceApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication app = new Builder(args)
                .appPrefix("refdatams")
                .applicationClass(RefDataMicroServiceApplication.class)
                .build();
        app.run(args);
    }
}
