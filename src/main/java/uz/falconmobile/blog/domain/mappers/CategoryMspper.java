package uz.falconmobile.blog.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import uz.falconmobile.blog.domain.PostStatus;
import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.domain.entities.Post;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface CategoryMspper {

    @Mapping(target = "postCount" , source = "posts" , qualifiedByName = "calculatePostCount ")
    CategoryDto toDto(Category category);

    @Named("calculatePostCount ")
    default long calculatePostCount (List<Post> posts){

        if(posts == null || posts.isEmpty()){
            return 0;
        }

      return   posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();

    }
}
