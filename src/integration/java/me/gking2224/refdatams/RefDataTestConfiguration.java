package me.gking2224.refdatams;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.test.CommonTestConfiguration;
import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;
import me.gking2224.refdatams.db.JpaConfiguration;
import me.gking2224.refdatams.web.WebAppConfiguration;

@Import({CommonTestConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, JpaConfiguration.class, BatchConfiguration.class, WebAppConfiguration.class})
@ComponentScan({"me.gking2224.refdatams.model", "me.gking2224.refdatams.service"})
public class RefDataTestConfiguration {

}
