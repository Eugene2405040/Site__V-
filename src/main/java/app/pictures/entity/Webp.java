package app.pictures.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "images_webp", indexes = @Index(name = "uniqueIndex", columnList = "path", unique = true))
public class Webp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public LocalDateTime getLoaded() {
        return loaded;
    }

    public void setLoaded(LocalDateTime loaded) {
        this.loaded = loaded;
    }

    @Lob
    @Column(length = 1000000)
    private byte[] body;

    @Column(name = "loaded")
    private LocalDateTime loaded;

    @PrePersist
    private void init(){
        loaded = LocalDateTime.now();
    }
}
//    @Column(columnDefinition = "MEDIUMTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL")
