package com.example.wastepickup.controller;

import com.example.wastepickup.entity.Schedule;
import com.example.wastepickup.service.WastePickupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired private WastePickupService service;

    @PostMapping
    public Schedule createSchedule(@Valid @RequestBody Schedule schedule) {
        return service.createSchedule(schedule);
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return service.getAllSchedules();
    }
}