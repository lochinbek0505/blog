package uz.falconmobile.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.falconmobile.blog.domain.entities.Post;

import java.util.UUID;

@Repository

public interface PostRepository extends JpaRepository<Post, UUID> {
}
