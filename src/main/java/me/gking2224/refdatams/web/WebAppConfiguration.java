package me.gking2224.refdatams.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import me.gking2224.common.web.CommonWebAppConfiguration;
import me.gking2224.common.web.WebConfigurationOptions;

@Profile("web")
@ComponentScan({"me.gking2224.refdatams.web"})
@Import(CommonWebAppConfiguration.class)
@EnableWebMvc
public class WebAppConfiguration {
    
    @Bean
    public WebConfigurationOptions getConfig() {
        return new WebConfigurationOptions() {

            @Override
            public String[] getAllowedCorsOrigins() {
                return new String[] {"http://localhost:8080"};
            }

            @Override
            public String[] getAllowedCorsMethods() {
                return null;
            }
            
        };
    }
}
