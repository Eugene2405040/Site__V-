package app;

import app.pictures.util.WebpImageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args){

//        WebpImageConverter.printSupportedParams();
        SpringApplication.run(Main.class, args);
    }

// TODO: robots.txt
//          security
//          ssl,
//          self restart
//          nested categories
//          html tag handler with js in admin/create(update)
//          read about @PrePersist
//          Create visits counter with entity / table
//          html, css (view) - implement pages template
//

}
