package com.utkarsh.baigos.repositories;

import com.utkarsh.baigos.entities.Category;
import com.utkarsh.baigos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findByCategoryId(int id);
}
