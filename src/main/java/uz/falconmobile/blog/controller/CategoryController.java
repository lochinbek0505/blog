package uz.falconmobile.blog.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.dtos.CreateCategoryRequest;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.domain.mappers.CategoryMapper;
import uz.falconmobile.blog.services.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {

        List<CategoryDto> categories = categoryService.listCategories().stream().map(categoryMapper::toDto).toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody @Valid
            CreateCategoryRequest createCategoryRequest)  {


        Category category = categoryMapper.toEntity(createCategoryRequest);
        categoryService.createCategory(category);

        return  new ResponseEntity<>(
                categoryMapper.toDto(category), HttpStatus.CREATED
        );


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
