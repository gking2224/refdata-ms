package me.gking2224.refdatams.db;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("embedded")
@ComponentScan({"me.gking2224.refdatams.db"})
public class EmbeddedDatabaseConfiguration {
    
}
