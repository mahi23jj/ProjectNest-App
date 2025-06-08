// package com.toolkit.controller;

// import com.toolkit.dto.AuthResponse;
// import com.toolkit.dto.LoginRequest;
// import com.toolkit.entity.User;
// import com.toolkit.repository.UserRepository;
// import com.toolkit.service.JwtUtil;

// import lombok.RequiredArgsConstructor;
// import org.springframework.http.*;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// @CrossOrigin(origins = "*")
// @RequiredArgsConstructor
// public class AuthController {

//     private final UserRepository userRepo;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody LoginRequest req) {
//         if (userRepo.findByUsername(req.getUsername()).isPresent()) {
//             return ResponseEntity.badRequest().body("Username already exists");
//         }

//         User user = new User(null, req.getUsername(), req.getUsername() + "@example.com",
//                 passwordEncoder.encode(req.getPassword()));
//         userRepo.save(user);

//         return ResponseEntity.ok("User registered");
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest req) {
//         var user = userRepo.findByUsername(req.getUsername())
//                 .orElse(null);

//         if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//         }

//         String token = jwtUtil.generateToken(user.getUsername());

//         return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername()));
//     }
// }
