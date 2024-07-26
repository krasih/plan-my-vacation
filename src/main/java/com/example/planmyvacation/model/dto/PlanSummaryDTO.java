package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.util.Utils;

import java.time.LocalDate;
import java.util.List;

public class PlanSummaryDTO {

    private long id;

    private String city;

    private String country;

    private LocalDate startDate;

    private LocalDate endDate;

    private User user;

    private boolean active;

    public PlanSummaryDTO() {
    }

    public long getId() {
        return id;
    }

    public PlanSummaryDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public PlanSummaryDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public PlanSummaryDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public PlanSummaryDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public PlanSummaryDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public PlanSummaryDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public PlanSummaryDTO setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getLocation() { return city + ", " + country; }

    public String getDates() {
        return Utils.formatDate(startDate) + " - " + Utils.formatDate(endDate);
    }

    public String getUsername() { return user.getUsername(); }
}
