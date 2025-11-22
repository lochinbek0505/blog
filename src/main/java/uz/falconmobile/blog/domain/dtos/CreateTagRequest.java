package uz.falconmobile.blog.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest {

    @NotEmpty(message = "Tag name must not be empty")
    @Size(max = 10, message = "Tag name must not exceed {max} characters")
    private  Set<
            @Size(min=2 , max = 30, message = "Each tag name must not exceed {max} characters")
                    @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters, numbers, spaces, hyphens, and underscores")
            String> tagNames;


}
