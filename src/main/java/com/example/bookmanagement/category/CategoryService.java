package com.example.bookmanagement.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public void addNewCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findCategoriesByCategoryName(category.getCategoryName());
        if (optionalCategory.isPresent()) throw new IllegalStateException("This category already exist");
        categoryRepository.save(category);
    }
}
