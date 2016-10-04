package me.gking2224.refdatams.mvc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import me.gking2224.common.utils.test.WebAppTestConfigurer;
import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.db.EmbeddedDatabaseConfiguration;
import me.gking2224.refdatams.web.WebAppConfiguration;

@ComponentScan({"me.gking2224.refdatams.model", "me.gking2224.refdatams.service", "me.gking2224.common"})
@Import({BatchConfiguration.class, WebAppConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class})
//@EnableAutoConfiguration
@ImportResource("classpath:test-config.xml")
@EnableWebMvc
public class RefDataWebAppTestConfigurer extends WebAppTestConfigurer {
}
