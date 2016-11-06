package me.gking2224.refdatams.mvc;


import org.springframework.context.annotation.Import;

import me.gking2224.common.test.WebAppTestConfigurer;
import me.gking2224.refdatams.RefDataTestConfiguration;

@Import({RefDataTestConfiguration.class})
@org.springframework.test.context.web.WebAppConfiguration
public class RefDataWebAppTestConfiguration extends WebAppTestConfigurer {
}
