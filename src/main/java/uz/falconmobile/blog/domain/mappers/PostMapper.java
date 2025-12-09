package uz.falconmobile.blog.domain.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.falconmobile.blog.domain.CreatePostRequest;
import uz.falconmobile.blog.domain.UpdatePostRequest;
import uz.falconmobile.blog.domain.dtos.CreatePostRequestDto;
import uz.falconmobile.blog.domain.dtos.PostDto;
import uz.falconmobile.blog.domain.dtos.UpdatePostRequestDto;
import uz.falconmobile.blog.domain.entities.Post;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto postDto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);
}
