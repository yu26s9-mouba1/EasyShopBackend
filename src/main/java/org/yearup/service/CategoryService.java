package org.yearup.service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // get category by id
    public Category getById(int categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Creates category
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    // updates category and return the updated category
    public Category update(int categoryId, Category category) {
       category.setCategoryId(categoryId);
        return categoryRepository.save(category);
    }

    // deletes category
    public void delete(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
