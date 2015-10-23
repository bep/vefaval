package is.bep.vefaval;

import no.difi.vefa.validator.Validator;
import no.difi.vefa.validator.ValidatorBuilder;
import no.difi.vefa.validator.api.Source;
import no.difi.vefa.validator.properties.SimpleProperties;
import no.difi.vefa.validator.source.DirectorySource;
import no.difi.vefa.validator.source.RepositorySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Paths;

/**
 * Main entry application and config.
 */
@SpringBootApplication
@PropertySource("classpath:validator.properties")
public class App {

    @Value("${source}")
    private String propSource;
    @Value("${repository}")
    private String propRepository;
    @Value("${directory}")
    private String dirRules;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    Validator validator() throws Exception {
        Source source;
        switch (propSource) {
            case "directory":
                source = new DirectorySource(Paths.get(dirRules));
                break;
            case "repository":
                source = new RepositorySource(propRepository);
                break;
            default:
                throw new Exception("Type of source not recognized.");
        }

        SimpleProperties config = new SimpleProperties();

        return ValidatorBuilder
                .newValidator()
                .setProperties(config)
                .setSource(source)
                .build();
    }

}
