package app.repository;

import app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query(nativeQuery = true, value = "select c.page_id from category c where c.path = ?1")
//    Optional<Long> findPageIdByPath(String path);
}
