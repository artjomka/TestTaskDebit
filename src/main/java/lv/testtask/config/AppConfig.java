package lv.testtask.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "lv.testtask.config", excludeFilters = @ComponentScan.Filter(value = MvcConfig.class, type = FilterType.CUSTOM))
public class AppConfig {
}
