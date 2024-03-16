package app.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//  TODO: validate output
@Controller
public class SiteMapXMLController {
    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String siteMapXML(){
        return "sitemap.xml";    }

}
