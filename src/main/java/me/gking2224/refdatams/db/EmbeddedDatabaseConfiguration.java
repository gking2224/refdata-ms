package me.gking2224.refdatams.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import me.gking2224.common.db.embedded.EmbeddedDatabaseOptions;

@Profile("embedded")
@ComponentScan({"me.gking2224.refdatams.db"})
@Import(me.gking2224.common.db.embedded.EmbeddedDatabaseConfiguration.class)
public class EmbeddedDatabaseConfiguration {
    
    @Bean
    public EmbeddedDatabaseOptions getOptions() {
        return new EmbeddedDatabaseOptions() {
            
            @Override
            public String[] getScripts() {
                return new String[] {"/db/01_model.sql"};
            }
        };
    }
}
