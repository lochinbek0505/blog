package uz.falconmobile.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.falconmobile.blog.domain.CreatePostRequest;
import uz.falconmobile.blog.domain.UpdatePostRequest;
import uz.falconmobile.blog.domain.dtos.CreatePostRequestDto;
import uz.falconmobile.blog.domain.dtos.PostDto;
import uz.falconmobile.blog.domain.dtos.UpdatePostRequestDto;
import uz.falconmobile.blog.domain.entities.Post;
import uz.falconmobile.blog.domain.entities.User;
import uz.falconmobile.blog.domain.mappers.PostMapper;
import uz.falconmobile.blog.services.PostService;
import uz.falconmobile.blog.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId

    ) {

        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getDraftPosts(@RequestAttribute UUID userId) {
        User loggedUser = userService.getUserById(userId);
        List<Post> drafts = postService.getDraftPosts(loggedUser);
        List<PostDto> postDtos = drafts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto,
                                              @Valid @RequestAttribute UUID userId) {
        User loggedUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createdPost = postService.createPost(loggedUser, createPostRequest);
        PostDto postDto = postMapper.toDto(createdPost);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);

    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID postId, @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatedPost = postService.updatePost(postId, updatePostRequest);

        return new ResponseEntity<>(postMapper.toDto(updatedPost), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable UUID id) {
        Post post = postService.getPostById(id);
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }
}
