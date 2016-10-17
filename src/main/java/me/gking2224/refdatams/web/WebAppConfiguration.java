package me.gking2224.refdatams.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import me.gking2224.common.web.CommonWebAppConfiguration;

@Profile("web")
@ComponentScan({"me.gking2224.refdatams.web"})
@Import(CommonWebAppConfiguration.class)
@EnableWebMvc
public class WebAppConfiguration {
}
