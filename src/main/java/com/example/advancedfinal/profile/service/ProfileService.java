package com.example.advancedfinal.profile.service;
import org.springframework.stereotype.Service;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;
import com.example.advancedfinal.profile.entity.profile;
import com.example.advancedfinal.profile.repository.profilerepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final profilerepository  profileRepository;
        private final UserRepository userRepository;

    public  profile createProfile(Long userId,String name, String email, String department, String academicYear, String bio) {
               User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        profile profile = new profile();
        profile.setName(name);
        profile.setEmail(email);
        profile.setDepartment(department);
        profile.setAcademicYear(academicYear);
        profile.setBio(bio);
        profile.setUser(user);

        profileRepository.save(profile);

        return profile;
    }
 // display the created profile


    //  public Profile getProfileByUserId(Long userId) {
    //     return profileRepository.findByUserId(userId)
    //             .orElseThrow(() -> new RuntimeException("Profile not found for user " + userId));
    // }
  
}





