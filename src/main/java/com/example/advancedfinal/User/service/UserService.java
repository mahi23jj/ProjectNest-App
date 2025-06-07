package com.example.advancedfinal.User.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // public void registerUser(String username, String password) {
    //     User user = new User(username, passwordEncoder.encode(password));
    //     userRepository.save(user);
    // }

    @Transactional
    public Long registerUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password); // You should encode it in real apps
    return userRepository.save(user).getId();
}


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}

