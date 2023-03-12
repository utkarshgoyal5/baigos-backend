package com.utkarsh.baigos.services.impl;

import com.utkarsh.baigos.entities.Category;
import com.utkarsh.baigos.entities.User;
import com.utkarsh.baigos.exceptions.ResourceNotFoundException;
import com.utkarsh.baigos.payloads.CategoryDto;
import com.utkarsh.baigos.payloads.UserDto;
import com.utkarsh.baigos.repositories.CategoryRepo;
import com.utkarsh.baigos.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        categoryRepo.save(category);
        return this.categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {

        Category category = categoryRepo.findByCategoryId(categoryDto.getCategoryId());

        if(null == category) {
            throw new ResourceNotFoundException("Category", "category Id", categoryDto.getCategoryId());
        }

        category.setCategoryId(categoryDto.getCategoryId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepo.save(category);

        return this.categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        Category category = categoryRepo.findByCategoryId(categoryId);

        if(null == category) {
            throw new ResourceNotFoundException("Category", "category Id", categoryId);
        }

        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = categoryRepo.findAll();

        List<CategoryDto> allCategoriesDto = allCategories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());

        return allCategoriesDto;
    }

    @Override
    public void deleteCategory(int categoryId) {

        Category category = categoryRepo.findByCategoryId(categoryId);

        if(null == category) {
            throw new ResourceNotFoundException("Category", "category Id", categoryId);
        }

        categoryRepo.delete(category);
    }

    private Category dtoToCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }

    private CategoryDto categoryToDto(Category category) {

        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }
}
