package me.gking2224.refdatams.db;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("!embedded")
@ComponentScan({"me.gking2224.refdatams.db"})
@Import(me.gking2224.common.db.CommonDatabaseConfiguration.class)
public class DatabaseConfiguration {

}
