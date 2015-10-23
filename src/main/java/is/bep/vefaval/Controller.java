package is.bep.vefaval;

import no.difi.vefa.validator.Validation;
import no.difi.vefa.validator.Validator;
import no.difi.vefa.validator.ValidatorBuilder;
import no.difi.vefa.validator.api.Source;
import no.difi.vefa.validator.properties.SimpleProperties;
import no.difi.vefa.validator.source.DirectorySource;
import no.difi.vefa.validator.source.RepositorySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

/**
 * REST Vefa Validator.
 */
@RestController
public class Controller {

    Validator validator;

    @Value("${source}")
    private String propSource;
    @Value("${repository}")
    private String propRepository;
    @Value("${directory}")
    private String dirRules;


    @RequestMapping("/")
    public String index() {
        return "Vefa validator: POST a file to the /validate endpoint.";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Validation validate(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream is = new ByteArrayInputStream(file.getBytes());

        if ("application/x-gzip".equals(file.getContentType())) {
            is = new GZIPInputStream(is);
        }

        return validator.validate(is);
    }


    // Borrowed/adapted from https://github.com/difi/vefa-validator/blob/master/validator-web/src/main/java/no/difi/vefa/validator/service/ValidatorService.java
    // TODO: Do something.
    @PostConstruct
    public void postConstruct() throws Exception {
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

        validator = ValidatorBuilder
                .newValidator()
                .setProperties(config)
                .setSource(source)
                .build();
    }
}

