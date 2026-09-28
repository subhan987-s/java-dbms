package com.example.wastepickup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "households")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Household name is required")
    private String householdName;

    @NotBlank(message = "Address is required")
    private String address;

    private Double averageSegregationScore = 0.0;
    private Boolean flaggedForNotice = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;
}