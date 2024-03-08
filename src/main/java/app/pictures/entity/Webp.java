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
