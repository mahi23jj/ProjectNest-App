package com.example.advancedfinal.User.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Uncomment and configure if you want password hashing
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean registerUser(String username, String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Use encoder if enabled
        // user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        // If using password encoder:
        // return passwordEncoder.matches(password, user.getPassword());

        return user.getPassword().equals(password);
    }

    // get ny useir by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
        
    }

    public User getUserByUsername(String username) {
    return userRepository.findByUsername(username); // use your actual repo method
}


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // delete all users
    public void deleteAllUsers() {
        userRepository.deleteAll();
        
    }
}
