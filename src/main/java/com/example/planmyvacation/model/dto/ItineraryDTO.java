package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.util.Utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ItineraryDTO {

    private long id;

    private LocalDate date;

    private int dayNo;

    private boolean isLastDay;

    private List<ActivityDTO> activities;

    private Plan plan;

    public ItineraryDTO() {
    }

    public long getId() {
        return id;
    }

    public ItineraryDTO setId(long id) {
        this.id = id;
        return this;
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

//    public Date getDateAsDate() {
//        return java.sql.Date.valueOf(date);
//    }

    public String getDateAsString() {
//        return Utils.formatDate(date, "E, dd/MM");
        return Utils.formatDate(date, "E");
    }
}
