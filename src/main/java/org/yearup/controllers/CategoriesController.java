package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.service.CategoryService;
import org.yearup.service.ProductService;
import java.util.List;

/**
 * Rest controller that handles all category-related API endpoints.
 */
@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryService categoryService;
    private ProductService productService;


    // Constructor injection for categoryService and ProductService
    @Autowired
    public CategoriesController(CategoryService categoryService, ProductService productService){
        this.categoryService = categoryService;
        this.productService = productService;
    }

    //Gets and returns all categories
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    // get a category by its id
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    // get all product by categoryId
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productService.search(categoryId, null, null, null);
    }

    // Creates a new category and returns it HTTP 201 (created)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category creeatedCategory = categoryService.create(category);
        return ResponseEntity.ok(creeatedCategory);
    }

    // Updates an existing category using the provided ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }


    // delete the category by id and return status 204 No
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
