package uz.falconmobile.blog.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.falconmobile.blog.domain.CreatePostRequest;
import uz.falconmobile.blog.domain.PostStatus;
import uz.falconmobile.blog.domain.UpdatePostRequest;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.domain.entities.Post;
import uz.falconmobile.blog.domain.entities.Tag;
import uz.falconmobile.blog.domain.entities.User;
import uz.falconmobile.blog.repositories.PostRepository;
import uz.falconmobile.blog.services.CategoryService;
import uz.falconmobile.blog.services.PostService;
import uz.falconmobile.blog.services.TagService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final int WorldsPerMinute = 200;

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId == null || tagId == null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryIdAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }
        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Transactional
    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        post.setAuthor(user);
        post.setStatus(createPostRequest.getStatus());
        post.setReadingTime(calculateReadTime(createPostRequest.getContent()));
        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        post.setCategory(category);
        Set<UUID> tagIds = createPostRequest.getTagIds();
        List<Tag> tags = tagService.getTagsByIds(tagIds);
        post.setTags(new HashSet<>(tags));

        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(UUID postId, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        existingPost.setTitle(updatePostRequest.getTitle());
        String postContent = updatePostRequest.getContent();
        existingPost.setContent(postContent);
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadTime(updatePostRequest.getContent()));

        UUID updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if (!existingPost.getCategory().getId().equals(updatePostRequestCategoryId)) {
            Category newCategory = categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(newCategory);
        }

        Set<UUID> existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        Set<UUID> updateTagIds = updatePostRequest.getTagIds();
        if (!existingTagIds.equals(updateTagIds)) {
            List<Tag> updatedTags = tagService.getTagsByIds(updateTagIds);
            existingPost.setTags(new HashSet<>(updatedTags));
        }


        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(UUID postId) {
        Post post=getPostById(postId);
        postRepository.delete(post);
    }

    private Integer calculateReadTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }
        String[] words = content.trim().split("\\s+");
        int wordCount = words.length;
        return (int) Math.ceil((double) wordCount / WorldsPerMinute);
    }
}
