package com.utkarsh.baigos.repositories;

import com.utkarsh.baigos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findById(int id);

    Optional<User> findByEmail(String email);
}
