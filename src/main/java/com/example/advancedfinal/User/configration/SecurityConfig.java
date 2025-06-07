// package com.example.advancedfinal.User.configration;


// import com.example.advancedfinal.User.service.UserService;
// import com.example.advancedfinal.User.entity.User;
// import com.example.advancedfinal.User.repository.UserRepository;



// import org.springframework.beans.factory.annotation.Autowired;
// //  // Spring Security User
// import org.springframework.context.annotation.*;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private UserRepository userRepository;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return username -> {
//             User user = userRepository.findByUsername(username);
//             if (user == null) {
//                 throw new UsernameNotFoundException("User not found");
//             }
//             return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
//                     .password(user.getPassword())
//                     .roles("USER")
//                     .build();
//         };
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable() // For simplicity, disable CSRF (enable in production!)
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/register","/api/users").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginProcessingUrl("/login") // POST login here
//                 .defaultSuccessUrl("/auth/login-success", true)
//                 .failureUrl("/auth/login-fail")
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .logoutSuccessUrl("/auth/login-fail")
//             );

//         return http.build();
//     }
// }

