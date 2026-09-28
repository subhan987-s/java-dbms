package com.example.wastepickup.service;

import com.example.wastepickup.entity.*;
import com.example.wastepickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WastePickupService {

    @Autowired private ZoneRepository zoneRepo;
    @Autowired private ScheduleRepository scheduleRepo;
    @Autowired private HouseholdRepository householdRepo;
    @Autowired private PickupLogRepository pickupLogRepo;

    public Zone createZone(Zone zone) {
        return zoneRepo.save(zone);
    }

    public Schedule createSchedule(Schedule schedule) {
        Zone zone = zoneRepo.findById(schedule.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        schedule.setZone(zone);
        return scheduleRepo.save(schedule);
    }

    public Household createHousehold(Household household) {
        Zone zone = zoneRepo.findById(household.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        household.setZone(zone);
        return householdRepo.save(household);
    }

    public PickupLog logPickup(Long householdId, Integer score) {
        Household household = householdRepo.findById(householdId)
                .orElseThrow(() -> new RuntimeException("Household not found"));

        PickupLog log = new PickupLog();
        log.setHousehold(household);
        log.setSegregationScore(score);
        log.setPickupTimestamp(LocalDateTime.now());
        PickupLog savedLog = pickupLogRepo.save(log);

        // Calculate Rolling Average & Automated Flagging
        List<PickupLog> logs = pickupLogRepo.findByHouseholdId(householdId);
        double avg = logs.stream().mapToInt(PickupLog::getSegregationScore).average().orElse(0.0);

        household.setAverageSegregationScore(Math.round(avg * 100.0) / 100.0);
        household.setFlaggedForNotice(avg < 50.0);
        householdRepo.save(household);

        return savedLog;
    }

    public Double getZoneAverageScore(Long zoneId) {
        List<Household> households = householdRepo.findByZoneId(zoneId);
        return households.stream()
                .mapToDouble(Household::getAverageSegregationScore)
                .average().orElse(0.0);
    }

    public List<Zone> getAllZones() { return zoneRepo.findAll(); }
    public List<Schedule> getAllSchedules() { return scheduleRepo.findAll(); }
    public List<Household> getAllHouseholds() { return householdRepo.findAll(); }
    public List<PickupLog> getAllPickupLogs() { return pickupLogRepo.findAll(); }
}