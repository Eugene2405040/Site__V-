package app.junk.sitemap.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@AllArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "url")
public class UrlXML {
    @XmlElement(name = "loc")
    private String url;

    @XmlElement(name = "lastmod")
    private String lastModified;
}
