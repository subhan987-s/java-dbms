package com.example.wastepickup.controller;

import com.example.wastepickup.entity.Zone;
import com.example.wastepickup.service.WastePickupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired private WastePickupService service;

    @PostMapping
    public Zone createZone(@Valid @RequestBody Zone zone) {
        return service.createZone(zone);
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return service.getAllZones();
    }

    @GetMapping("/{zoneId}/average-score")
    public Double getZoneAverage(@PathVariable Long zoneId) {
        return service.getZoneAverageScore(zoneId);
    }
}