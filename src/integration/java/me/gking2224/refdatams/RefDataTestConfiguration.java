package me.gking2224.refdatams;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import me.gking2224.common.test.CommonTestConfiguration;
import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;

@ComponentScan({"me.gking2224.refdatams.model", "me.gking2224.refdatams.service"})
@EnableJpaRepositories("me.gking2224.refdatams.db.jpa")
@Import({BatchConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, CommonTestConfiguration.class})
public class RefDataTestConfiguration {

}
