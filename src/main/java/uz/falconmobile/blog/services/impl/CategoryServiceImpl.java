package uz.falconmobile.blog.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.repositories.CategoryRepository;
import uz.falconmobile.blog.services.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Category already exists");
        }else{
            return  categoryRepository.save(category);
        }
    }
}
