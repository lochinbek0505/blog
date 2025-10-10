package uz.falconmobile.blog.controller;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.domain.mappers.CategoryMspper;
import uz.falconmobile.blog.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMspper categoryMspper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {

        List<CategoryDto> categories = categoryService.listCategories().stream().map(categoryMspper::toDto).toList();

        return ResponseEntity.ok(categories);
    }

}
