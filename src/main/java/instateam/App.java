package instateam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mark on 5/29/2017.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class,args);
  }
}
