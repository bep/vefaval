package is.bep.vefaval;

import no.difi.vefa.validator.Validator;
import no.difi.vefa.validator.ValidatorBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main entry application and config.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    Validator validator() throws Exception {
        return ValidatorBuilder.newValidator().build();
    }
}
