package me.gking2224.refdatams.mvc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.test.TestConfiguration;
import me.gking2224.common.test.WebAppTestConfigurer;
import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;
import me.gking2224.refdatams.web.WebAppConfiguration;

@ComponentScan({"me.gking2224.refdatams.model", "me.gking2224.refdatams.service"})
@Import({BatchConfiguration.class, WebAppConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, TestConfiguration.class})
public class RefDataWebAppTestConfiguration extends WebAppTestConfigurer {
}
