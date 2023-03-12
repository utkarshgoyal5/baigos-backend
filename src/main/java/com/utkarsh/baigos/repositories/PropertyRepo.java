package com.utkarsh.baigos.repositories;

import com.utkarsh.baigos.entities.Category;
import com.utkarsh.baigos.entities.Property;
import com.utkarsh.baigos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepo extends JpaRepository<Property, Integer> {
    List<Property> findByUser(User user);
    List<Property> findByCategory(Category category);
}
