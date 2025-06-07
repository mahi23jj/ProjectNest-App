package com.example.advancedfinal.User.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advancedfinal.User.entity.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // User findByEmail(String email);
    // User findById(Long id);
    
}

