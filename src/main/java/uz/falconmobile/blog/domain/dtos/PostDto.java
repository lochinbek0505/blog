package uz.falconmobile.blog.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uz.falconmobile.blog.domain.PostStatus;
import uz.falconmobile.blog.domain.entities.Tag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private AuthorDto author;
    private CategoryDto category;
    private Set<Tag> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostStatus status;

}
