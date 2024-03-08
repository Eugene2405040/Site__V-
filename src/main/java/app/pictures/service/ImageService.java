package app.pictures.service;

import app.pictures.entity.Webp;
import app.pictures.repository.ImageRepository;
import app.pictures.util.WebpImageConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @SneakyThrows
    public final ResponseEntity<?> loadPicture(String path, MultipartFile file) {

        if (WebpImageConverter.isContentTypeNotSupported(file.getContentType())) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Format <b>\" " + file.getContentType() + " \"</b> is not supported.");
        }

        var body = WebpImageConverter.convertImageToWebp(file);

        Webp picture = new Webp();
        picture.setPath(path + ".webp");
        picture.setBody(body);

        try {
            imageRepository.save(picture);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("path", picture.getPath())
                    .contentType(MediaType.parseMediaType("image/webp"))
                    .contentLength(picture.getBody().length)
                    .body(e);
        }

        var inputStreamResource = new InputStreamResource(
                new ByteArrayInputStream(picture.getBody())
        );

        return ResponseEntity.ok()
                .header("path", picture.getPath())
                .contentType(MediaType.parseMediaType("image/webp"))
                .contentLength(picture.getBody().length)
                .body(inputStreamResource);
    }

    @SneakyThrows
    public ResponseEntity<?> getPicture(String path) {
        var picture = imageRepository.findByPath(path).orElse(null);

        if (picture == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        var pictureBody = new InputStreamResource(new ByteArrayInputStream(picture.getBody()));

        System.out.println(MediaType.parseMediaType("image/webp"));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/webp"))
                .contentLength(picture.getBody().length)
                .body(pictureBody);
    }

    public ResponseEntity<?> getAllPictures() {
        var pictures = imageRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Webp p : pictures) {
            builder.append("<img src=\"/pictures/")
                    .append(p.getPath()).append("\" alt=\"\" class=\"\" title=\"")
                    .append(p.getPath()).append("\" style=\"height:120px;margin:40px;\">\n");
        }
        return ResponseEntity.ok(builder.toString());
    }
}