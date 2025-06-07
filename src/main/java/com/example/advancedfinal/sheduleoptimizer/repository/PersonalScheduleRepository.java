package com.example.advancedfinal.sheduleoptimizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advancedfinal.sheduleoptimizer.entity.PersonalSchedule;

public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, Long> {
    List<PersonalSchedule> findByUserId(Long userId);
    List<PersonalSchedule> findByUserIdAndProjectNameContainingIgnoreCase(Long userId, String projectName); // ✅ added
}
