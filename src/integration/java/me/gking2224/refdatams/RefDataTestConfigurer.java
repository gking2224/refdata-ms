package me.gking2224.refdatams;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;

@ComponentScan({"me.gking2224.refdatams.model", "me.gking2224.refdatams.service", "me.gking2224.common"})
@Import({BatchConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class})
@ImportResource("classpath:test-config.xml")
public class RefDataTestConfigurer {

}
