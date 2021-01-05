package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(DataSourceConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class BaseConfig {


}
