package com.example.advancedfinal.User.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // to get users
    @GetMapping("/values")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
 

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "User registered successfully";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "Login successful!";
    }

    @GetMapping("/login-fail")
    public String loginFail() {
        return "Login failed!";
    }
}

