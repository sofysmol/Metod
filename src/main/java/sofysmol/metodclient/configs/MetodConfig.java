package sofysmol.metodclient.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;

/**
 * Created by sofysmo on 08.10.16.
 */
@Configuration
public class MetodConfig {
    @Resource
    private Environment env;
    @Bean
    public  DriverManagerDataSource dataSourse() {
        DriverManagerDataSource ds = new DriverManagerDataSource(env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        return ds;
    }
}
