package com.company.controllers;

import com.company.controllers.interfaces.ICategoryController;
import com.company.models.Category;
import com.company.repositories.interfaces.ICategoryRepository;

import java.util.List;

public class CategoryController implements ICategoryController {

    private final ICategoryRepository repo;

    public CategoryController(ICategoryRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String getAllCategories() {
        List<Category> categories = repo.getAllCategories();
        if (categories == null) {
            return "Error fetching categories";
        }

        StringBuilder response = new StringBuilder();
        for (Category category : categories) {
            response.append(category.toString()).append("\n");
        }

        return response.toString();
    }

    public String createCategory(String name) {
        if (name == null || name.isEmpty()) {
            return "Invalid category name";
        }

        Category category = new Category(name);
        boolean created = repo.createCategory(category);

        return (created ? "Category created" : "An error occurred");
    }
}
