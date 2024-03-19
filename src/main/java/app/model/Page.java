package app.model;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "page")
@ToString
@Validated
public class Page {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSeoBlock() {
        return seoBlock;
    }

    public void setSeoBlock(String seoBlock) {
        this.seoBlock = seoBlock;
    }

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
