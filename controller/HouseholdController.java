package com.example.wastepickup.controller;

import com.example.wastepickup.entity.Household;
import com.example.wastepickup.service.WastePickupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired private WastePickupService service;

    @PostMapping
    public Household createHousehold(@Valid @RequestBody Household household) {
        return service.createHousehold(household);
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return service.getAllHouseholds();
    }
}