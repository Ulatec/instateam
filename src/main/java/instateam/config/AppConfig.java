package instateam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * Created by mark on 5/31/2017.
 */
@Configuration
@PropertySource("app.properties")
public class AppConfig {
  @Autowired
  private Environment env;

}
