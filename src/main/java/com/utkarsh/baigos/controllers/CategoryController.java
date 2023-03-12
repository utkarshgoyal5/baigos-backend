package com.utkarsh.baigos.controllers;

import com.utkarsh.baigos.payloads.ApiResponse;
import com.utkarsh.baigos.payloads.CategoryDto;
import com.utkarsh.baigos.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("category_id") Integer categoryId) {
        CategoryDto getCategoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(getCategoryDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> getAllCategoriesDto = categoryService.getAllCategories();
        return new ResponseEntity<>(getAllCategoriesDto, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("category_id") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", "2000", true), HttpStatus.OK);
    }
}
