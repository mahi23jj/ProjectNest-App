package com.example.advancedfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;
import com.example.advancedfinal.User.service.UserService;
import com.example.advancedfinal.profile.service.ProfileService;

@SpringBootApplication
public class AdvancedfinalApplication {


    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(AdvancedfinalApplication.class, args);
        // UserService userService = context.getBean(UserService.class);
        // ProfileService profileService = context.getBean(ProfileService.class);
        
        // Long userid=userService.registerUser("kalkidan", "Password123");
        // profileService.createProfile(userid, "Mahi", "mahletsol@example.com", "Software", "4rd Year", "Aspiring backend developer");

        // System.out.println("User and Profile created successfully!");

        // // System.out.println("User saved successfully!");
 
   



    }

}
