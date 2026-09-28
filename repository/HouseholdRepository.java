package com.example.wastepickup.repository;

import com.example.wastepickup.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    List<Household> findByZoneId(Long zoneId);
}