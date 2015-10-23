package is.bep.vefaval;

import no.difi.vefa.validator.Validation;
import no.difi.vefa.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * REST Vefa Validator.
 */
@RestController
public class Controller {

    @Autowired
    Validator validator;

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

}

