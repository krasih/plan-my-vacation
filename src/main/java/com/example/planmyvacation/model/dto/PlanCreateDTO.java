package com.example.planmyvacation.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PlanCreateDTO {

    private long id;

    @NotBlank(message = "{msg.city.not.empty}")
    private String cityName;

    @NotNull(message = "{msg.date.not.empty}")
    private LocalDate startDate;

    @NotNull(message = "{msg.date.not.empty}")
    private LocalDate endDate;

    public PlanCreateDTO() {
    }

    public long getId() {
        return id;
    }

    public PlanCreateDTO setId(long id) {
        this.id = id;
        return this;
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
