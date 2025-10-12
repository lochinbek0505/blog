package uz.falconmobile.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.falconmobile.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

@Repository

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT  c from Category c LEFT join fetch c.posts")
    List<Category> findAllWithPostCount ();

    boolean existsByNameIgnoreCase(String name);
}
