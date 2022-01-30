package br.com.redutorurlrest.rest;

import br.com.redutorurlrest.domain.Url;
import br.com.redutorurlrest.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/encurtador-rest/api")
@CrossOrigin(origins = "*")
public class EncurtadorRest {

    public static final String HTTPS_PREFIX = "https://";

    @Autowired
    private UrlService urlService;

    @GetMapping("/test")
    @ResponseBody
    public String encurtador() {
        System.out.println("Entrou no encurtador");
        return "www.abc.com";
    }

    @PostMapping
    public ResponseEntity<Url> salvar(@RequestBody Url url) {
        return new ResponseEntity<>(urlService.salvarUrl(url), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public void redirectById(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Url idUrl = urlService.buscarPorId(id);
        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Location");
        httpServletResponse.setHeader("Location", HTTPS_PREFIX.concat(idUrl.getUrlOriginal()));
        httpServletResponse.setHeader("Connection", "Close");
    }


    @GetMapping("/codigo/{urlOriginal}")
    public ResponseEntity<List<Url>> encurtarMesmaUrl(@PathVariable String urlOriginal) {
        List<Url> urlList = urlService.buscarUrlOriginal(urlOriginal);
        return new ResponseEntity<>(urlList, HttpStatus.FOUND);
    }
}
