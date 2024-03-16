package app.junk.sitemap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

//@RestController
public class SiteMapXMLRestController {
    @GetMapping(value = "/sitemap.xml"
            , produces = MediaType.APPLICATION_XML_VALUE
    )
    public UrlSet siteMapXML() throws MalformedURLException {

        Set<Url> urlSet = new HashSet<>();
        urlSet.add(new Url("/path", "2020-05-02"));
        urlSet.add(new Url("/path1", "2024-05-02"));

        UrlSet result = new UrlSet(urlSet);
        return  result;
    }
}
