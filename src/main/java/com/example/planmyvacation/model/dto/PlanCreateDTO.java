package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Location;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public class PlanCreateDTO {

    private String cityName;

    private LocalDate startDate;

    private LocalDate endDate;

    public PlanCreateDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public PlanCreateDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public PlanCreateDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public PlanCreateDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }
}
