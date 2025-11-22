package uz.falconmobile.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.falconmobile.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag,  UUID> {

    @Query("SELECT t FROM Tag t LEFT JOIN FETCH t.posts")
    java.util.List<Tag> findAllWithPostsCount();

    List<Tag> findByNameIn(Set<String> names);

}
