package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.util.Utils;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class PlanDetailsDTO {

    private Location location;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<PlaceDTO> myPlaces;

    private List<ItineraryDTO> itineraries;

    private User user;

    private boolean active;

    public PlanDetailsDTO() {
    }

    public Location getLocation() {
        return location;
    }

    public PlanDetailsDTO setLocation(Location location) {
        this.location = location;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public PlanDetailsDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public PlanDetailsDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<PlaceDTO> getMyPlaces() {
        return myPlaces;
    }

    public PlanDetailsDTO setMyPlaces(List<PlaceDTO> myPlaces) {
        this.myPlaces = myPlaces;
        return this;
    }

    public List<ItineraryDTO> getItineraries() {
        return itineraries;
    }

    public PlanDetailsDTO setItineraries(List<ItineraryDTO> itineraries) {
        this.itineraries = itineraries;
        return this;
    }

    public User getUser() {
        return user;
    }

    public PlanDetailsDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public PlanDetailsDTO setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getDates() {
        return Utils.formatDate(startDate) + " - " + Utils.formatDate(endDate);
    }
}
