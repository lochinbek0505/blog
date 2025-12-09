package uz.falconmobile.blog.services;

import uz.falconmobile.blog.domain.CreatePostRequest;
import uz.falconmobile.blog.domain.UpdatePostRequest;
import uz.falconmobile.blog.domain.dtos.PostDto;
import uz.falconmobile.blog.domain.entities.Post;
import uz.falconmobile.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post getPostById(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID postId, UpdatePostRequest post);
    void deletePost(UUID postId);
}
