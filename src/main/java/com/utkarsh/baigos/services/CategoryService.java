package com.utkarsh.baigos.services;

import com.utkarsh.baigos.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(int categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(int categoryId);
}
