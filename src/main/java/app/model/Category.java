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
@Getter
@Setter
@Table(name = "category")
@Validated
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private long parentId;

    @Transient
    private final Set<Category> childrenSet =
            new TreeSet<>(Comparator.comparing(category -> category.sequence));

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
