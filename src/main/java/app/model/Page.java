package app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "page")
@Getter
@Setter
@ToString
@Validated
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 500)
    private String title;

    @Column(nullable = false, unique = true, length = 700)
    private String description;

//    @Column(name = "key_words")
    private String keyWords;

    private String h1;

    @Column(columnDefinition = "MEDIUMTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL")
    private String body;

    @Column(name = "seo_block", columnDefinition = "MEDIUMTEXT")
    private String seoBlock;
}
