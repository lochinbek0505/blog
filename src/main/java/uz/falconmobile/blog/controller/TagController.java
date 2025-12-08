package uz.falconmobile.blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.falconmobile.blog.domain.dtos.CreateTagRequest;
import uz.falconmobile.blog.domain.dtos.TagResponse;
import uz.falconmobile.blog.domain.entities.Tag;
import uz.falconmobile.blog.domain.mappers.TagMapper;
import uz.falconmobile.blog.services.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;


    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {

        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagResponses);


    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTag(@RequestBody CreateTagRequest createTagRequest) {
        List<Tag> savedTags = tagService.createTags(createTagRequest.getTagNames());
        savedTags.stream().map(tagMapper::toTagResponse).toList();
        List<TagResponse> createdTagResponse = savedTags.stream().map(tagMapper::toTagResponse).toList();

        return new  ResponseEntity<>(createdTagResponse, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTags(id);
        return  ResponseEntity.noContent().build();
    }
}
