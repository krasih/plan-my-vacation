package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Location;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class PlanCreateDTO {

    private Location location;

    private Instant startDate;

    private Instant endDate;

    public PlanCreateDTO() {
    }

    public Location getLocation() {
        return location;
    }

    public PlanCreateDTO setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public PlanCreateDTO setStartDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public PlanCreateDTO setEndDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }
}
