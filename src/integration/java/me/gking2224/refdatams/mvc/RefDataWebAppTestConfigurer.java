package me.gking2224.refdatams.mvc;


import org.springframework.context.annotation.ComponentScan;

import me.gking2224.common.utils.test.WebAppTestConfigurer;

@ComponentScan({"me.gking2224.refdatams", "me.gking2224.common"})
public class RefDataWebAppTestConfigurer extends WebAppTestConfigurer {
}
