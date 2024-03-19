package app.repository;

import app.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
    boolean existsByTitle(String title);

    boolean existsByDescription(String description);
}
