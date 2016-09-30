package me.gking2224.refdatams;


import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import me.gking2224.refdatams.batch.BatchConfiguration;
import me.gking2224.refdatams.db.DatabaseConfiguration;
import me.gking2224.refdatams.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.refdatams.service", "me.gking2224.refdatams.model", "me.gking2224.common"})
@EnableAutoConfiguration
@EnableWebMvc
@Import({BatchConfiguration.class, WebAppConfiguration.class, DatabaseConfiguration.class})
public class RefDataMicroServiceApplication extends SpringBootServletInitializer{
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        ConfigurableEnvironment environment = new StandardServletEnvironment();
        //        ApplicationListener<?> listeners = new ApplicationListenerImplementation(this);
//        ApplicationContextInitializer<?> initializers = new ServletContextApplicationContextInitializer(ServletContext ctx);
        return application
                .contextClass(AnnotationConfigEmbeddedWebApplicationContext.class)
                .environment(environment)
//                .listeners(listeners)
//                .initializers(initializers)
                .registerShutdownHook(true)
                .web(true)
                .logStartupInfo(true)
                .addCommandLineProperties(true)
                .sources(RefDataMicroServiceApplication.class)
                .beanNameGenerator(new AnnotationBeanNameGenerator())
                ;
    }
    public static void main(String[] args) {
        // http://stackoverflow.com/questions/38802437/upgrading-spring-boot-from-1-3-7-to-1-4-0-causing-nullpointerexception-in-authen
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(RefDataMicroServiceApplication.class, args);
    }
}