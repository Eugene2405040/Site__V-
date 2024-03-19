package app.pictures.controller;

import app.pictures.service.ImageService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pictures")
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> pictureUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file
    ) {
        return imageService.loadPicture(name.replaceAll(" ", "-"), file);
    }

    @GetMapping("/{path}")
    private ResponseEntity<?> getPictureByPath(@PathVariable String path) {
        return imageService.getPicture(path);
    }

    @GetMapping
    private ResponseEntity<?> getAllPictures(){
        return imageService.getAllPictures();
    }

    @GetMapping("/360/{path}")
    private ResponseEntity<?> getPictureWithWidth360(@PathVariable String path){
        return imageService.getPictureWidth360(path);
    }
}
