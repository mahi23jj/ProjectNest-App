package com.example.advancedfinal.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

       // get ny useir by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = userService.registerUser(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("User already exists or registration failed");
        }
    }

    // Login user
 @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    boolean isValid = userService.validateUser(user.getUsername(), user.getPassword());

    if (isValid) {
        // Get full user info (e.g., from DB)
        User fullUser = userService.getUserByUsername(user.getUsername());

        if (fullUser != null) {
            // Option 1: Return directly (Jackson will convert to JSON)
            return ResponseEntity.ok(fullUser);

            // Option 2 (manual map):
            /*
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", fullUser.getId());
            userInfo.put("username", fullUser.getUsername());
            userInfo.put("password", fullUser.getPassword()); // 🔒 not recommended
            return ResponseEntity.ok(userInfo);
            */
        } else {
            return ResponseEntity.status(404).body("User not found!");
        }
    } else {
        return ResponseEntity.status(401).body("Login failed!");
    }
}


    // delete all user
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("All users deleted successfully");
    }
}
