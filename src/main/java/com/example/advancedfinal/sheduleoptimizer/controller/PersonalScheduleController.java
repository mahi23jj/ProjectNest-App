package com.example.advancedfinal.sheduleoptimizer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.advancedfinal.sheduleoptimizer.entity.PersonalSchedule;
import com.example.advancedfinal.sheduleoptimizer.service.PersonalScheduleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/personal-schedule")
@RequiredArgsConstructor
public class PersonalScheduleController {

    private final PersonalScheduleService scheduleService;

    // ✅ 1. Create a new schedule
    @PostMapping("/{userId}")
    public ResponseEntity<PersonalSchedule> createSchedule(
            @PathVariable Long userId,
            @RequestParam String projectName,
            @RequestParam String courseName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline,
            @RequestParam String progressDescription
    ) {
        PersonalSchedule schedule = scheduleService.createSchedule(
                userId, projectName, courseName, deadline, progressDescription
        );
        return ResponseEntity.ok(schedule);
    }

    // ✅ 2. Get all schedules for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<PersonalSchedule>> getSchedules(@PathVariable Long userId) {
        List<PersonalSchedule> schedules = scheduleService.getUserSchedules(userId);
        return ResponseEntity.ok(schedules);
    }

    // ✅ 3. Search by project name
    @GetMapping("/{userId}/search")
    public ResponseEntity<List<PersonalSchedule>> searchByProjectName(
            @PathVariable Long userId,
            @RequestParam String projectName
    ) {
        List<PersonalSchedule> result = scheduleService.searchByProjectName(userId, projectName);
        return ResponseEntity.ok(result);
    }

    // ✅ 4. Update a schedule
    @PutMapping("/{scheduleId}")
    public ResponseEntity<PersonalSchedule> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestParam String projectName,
            @RequestParam String courseName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline,
            @RequestParam String progressDescription
    ) {
        PersonalSchedule updated = scheduleService.updateSchedule(
                scheduleId, projectName, courseName, deadline, progressDescription
        );
        return ResponseEntity.ok(updated);
    }

    // ✅ 5. Delete a schedule
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
