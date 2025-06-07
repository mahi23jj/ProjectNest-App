package com.example.advancedfinal.sheduleoptimizer.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;
import com.example.advancedfinal.sheduleoptimizer.entity.PersonalSchedule;
import com.example.advancedfinal.sheduleoptimizer.repository.PersonalScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalScheduleService {

    
    
    private final PersonalScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // ✅ Create new schedule
    public PersonalSchedule createSchedule(Long userId, String projectName, String courseName, LocalDate deadline, String progressDescription) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PersonalSchedule schedule = new PersonalSchedule();
        schedule.setUser(user);
        schedule.setProjectName(projectName);
        schedule.setCourseName(courseName);
        schedule.setDeadline(deadline);
        schedule.setProgressDescription(progressDescription);

        return scheduleRepository.save(schedule);
    }

    // ✅ Get all schedules for a specific user
    public List<PersonalSchedule> getUserSchedules(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }

    // ✅ Search by project name for specific user
    public List<PersonalSchedule> searchByProjectName(Long userId, String projectName) {
        return scheduleRepository.findByUserIdAndProjectNameContainingIgnoreCase(userId, projectName);
    }

    // ✅ Update schedule
    public PersonalSchedule updateSchedule(Long scheduleId, String projectName, String courseName, LocalDate deadline, String progressDescription) {
        PersonalSchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setProjectName(projectName);
        schedule.setCourseName(courseName);
        schedule.setDeadline(deadline);
        schedule.setProgressDescription(progressDescription);

        return scheduleRepository.save(schedule);
    }

    // ✅ Delete schedule
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}

