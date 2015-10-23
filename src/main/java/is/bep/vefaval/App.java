package is.bep.vefaval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Main entry application and config.
 */
@SpringBootApplication
@PropertySource("classpath:validator.properties")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
