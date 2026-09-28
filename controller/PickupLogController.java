package com.example.wastepickup.controller;

import com.example.wastepickup.entity.PickupLog;
import com.example.wastepickup.service.WastePickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickups")
public class PickupLogController {

    @Autowired private WastePickupService service;

    @PostMapping("/log")
    public PickupLog logPickup(
            @RequestParam Long householdId,
            @RequestParam Integer score) {
        return service.logPickup(householdId, score);
    }

    @GetMapping
    public List<PickupLog> getAllLogs() {
        return service.getAllPickupLogs();
    }
}