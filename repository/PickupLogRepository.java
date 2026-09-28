package com.example.wastepickup.repository;

import com.example.wastepickup.entity.PickupLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PickupLogRepository extends JpaRepository<PickupLog, Long> {
    List<PickupLog> findByHouseholdId(Long householdId);
}