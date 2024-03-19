package app.pictures.repository;

import app.pictures.entity.Webp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Webp, Long> {
    Optional<Webp> findByPath(String path);
}
