package uz.falconmobile.blog.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uz.falconmobile.blog.domain.PostStatus;
import uz.falconmobile.blog.domain.dtos.TagResponse;
import uz.falconmobile.blog.domain.entities.Post;
import uz.falconmobile.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount" , source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {

        if(posts==null){
            return 0;
        }
       return (int) posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();


    }

}
