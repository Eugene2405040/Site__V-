package app.junk.sitemap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Url {
    @JacksonXmlElementWrapper(useWrapping = false)
    private String loc;

    @JacksonXmlElementWrapper(useWrapping = false)
    private String lastmod;
}
