package uz.falconmobile.blog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {


    @NotBlank(message = "Category name is required")
    @Size(min = 2 , max = 20 , message = "Category name  must be between {min}  to {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$",message = "Category name is can only cantain letters , numbers , spaces and hypnes")
    private String name;
}
