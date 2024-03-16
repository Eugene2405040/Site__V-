package app.junk.sitemap.test;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;

//@RestController
public class RestControllerTest {
    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public UrlXMLSet response(){
        HashSet<UrlXML> urlXMLHashSet = new HashSet<UrlXML>();
        urlXMLHashSet.add(new UrlXML("/path", "2020-05-25"));
        UrlXMLSet urlXMLSet = new UrlXMLSet(urlXMLHashSet);
        return urlXMLSet;
    }
}
