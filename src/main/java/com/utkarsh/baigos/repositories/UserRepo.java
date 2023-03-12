package com.utkarsh.baigos.repositories;

import com.utkarsh.baigos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findById(int id);
}
