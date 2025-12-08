package uz.falconmobile.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.falconmobile.blog.domain.PostStatus;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.domain.entities.Post;
import uz.falconmobile.blog.domain.entities.Tag;

import java.util.List;
import java.util.UUID;

@Repository

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryIdAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);
}
