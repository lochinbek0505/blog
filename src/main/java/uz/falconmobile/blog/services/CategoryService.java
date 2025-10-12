package uz.falconmobile.blog.services;

import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.entities.Category;

import java.util.List;

public interface  CategoryService {

    List<Category > listCategories();
    Category createCategory(Category category);


}
