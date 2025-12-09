package uz.falconmobile.blog.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.falconmobile.blog.domain.PostStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequestDto {

    @NotNull(message = "id cannot be null")
    private UUID id;



    @NotBlank(message = "title cannot be blank")
    @Size(min = 3, max = 200, message = "title length must be between {min} and {max} characters")
    private String title;

    @NotBlank(message = "content cannot be blank")
    @Size(min = 10,max = 50000, message = "content length must be at least {min} and {max} characters")
    private String content;

    @NotNull(message = "categoryId cannot be null")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "A post can have at most {max} tags")
    private Set<UUID> tagIds=new HashSet<>();

    @NotNull(message = "status cannot be null")
    private PostStatus status;
}
