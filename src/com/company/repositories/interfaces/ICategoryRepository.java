package com.company.repositories.interfaces;

import com.company.models.Category;

import java.util.List;

public interface ICategoryRepository {
    boolean createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int id);
}
