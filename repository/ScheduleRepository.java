package com.example.wastepickup.repository;

import com.example.wastepickup.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByZoneId(Long zoneId);
}