package instateam.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Created by mark on 5/31/2017.
 */
@Configuration
@PropertySource("app.properties")
public class DataConfig {
  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean buildSessionFactory(){
    Resource configurationLocation = new ClassPathResource("hibernate.cfg.xml");
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setConfigLocation(configurationLocation);
    sessionFactory.setPackagesToScan(env.getProperty("instateam.packageName"));
    sessionFactory.setDataSource(dataSource());
    return sessionFactory;
  }
  @Bean
  public DataSource dataSource(){
    BasicDataSource basicDataSource = new BasicDataSource();
    // Driver class name
    basicDataSource.setDriverClassName(env.getProperty("instateam.db.driver"));
    // Set URL
    basicDataSource.setUrl(env.getProperty("instateam.db.url"));
    // Set username & password
    basicDataSource.setUsername(env.getProperty("instateam.db.username"));
    basicDataSource.setPassword(env.getProperty("instateam.db.password"));

    return basicDataSource;
  }
}
