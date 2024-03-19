package app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "category")
@Validated
public class Category {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Set<Category> getChildrenSet() {
        return childrenSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private long parentId;

    @Transient
    private final Set<Category> childrenSet =
            new TreeSet<>(Comparator.comparing(Category::getSequence));

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String path;

    @Column(nullable = false)
    private Long sequence;

    @Column(updatable = false)
    @CreationTimestamp
    private Date published;

    @LastModifiedDate
    private Date modified;

    @OneToOne(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL, orphanRemoval = true
//            , mappedBy = "category"
    )
    @JoinColumn(name = "page_id")
//    @MapsId
    private Page page;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "parentId = " + parentId + ", " +
                "name = " + name + ", " +
                "path = " + path + ", " +
                "sequence = " + sequence + ", " +
                "modified = " + modified + ", " +
                "Children = " + childrenSet + ", " +
                "Page = " + page.getId() + ")";
    }
}
