package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Plan;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class ItineraryDTO {

    private LocalDate date;

    private int dayNo;

    private boolean isLastDay;

    private List<ActivityDTO> activities;

    private Plan plan;

    public ItineraryDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public ItineraryDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getDayNo() {
        return dayNo;
    }

    public ItineraryDTO setDayNo(int dayNo) {
        this.dayNo = dayNo;
        return this;
    }

    public boolean isLastDay() {
        return isLastDay;
    }

    public ItineraryDTO setLastDay(boolean lastDay) {
        isLastDay = lastDay;
        return this;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public ItineraryDTO setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
        return this;
    }

    public Plan getPlan() {
        return plan;
    }

    public ItineraryDTO setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }
}
