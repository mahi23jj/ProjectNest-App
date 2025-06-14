package com.example.advancedfinal.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.advancedfinal.profile.entity.profile;
import com.example.advancedfinal.profile.service.ProfileService;

@RestController
@RequestMapping("api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<profile> createProfile(@PathVariable Long userId, @RequestBody profile profileRequest) {
        profile created = profileService.createProfile(
            userId,
            profileRequest.getName(),
            profileRequest.getEmail(),
            profileRequest.getDepartment(),
            profileRequest.getAcademicYear(),
            profileRequest.getBio()
        );
        return ResponseEntity.ok(created);
    }

//   @GetMapping("/{userId}")
//     public ResponseEntity<Profile> getProfileByUser(@PathVariable Long userId) {
//         Profile profile = profileService.getProfileByUserId(userId);
//         return ResponseEntity.ok(profile);
//     }
}
