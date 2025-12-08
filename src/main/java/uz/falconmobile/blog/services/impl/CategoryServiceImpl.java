package uz.falconmobile.blog.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.falconmobile.blog.domain.dtos.CategoryDto;
import uz.falconmobile.blog.domain.entities.Category;
import uz.falconmobile.blog.repositories.CategoryRepository;
import uz.falconmobile.blog.services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists");
        } else {
            return categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(UUID id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            if (category.get().getPosts().size() > 0) {
                throw new IllegalStateException("Category has  posts associated with it");
            }
            categoryRepository.delete(category.get());
        }

    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category id not found " + id));
    }
}
