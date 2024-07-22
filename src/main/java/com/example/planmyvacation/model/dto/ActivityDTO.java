package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.*;
import com.example.planmyvacation.model.enums.PlaceType;
import jakarta.persistence.ManyToOne;

import java.util.Set;

public class ActivityDTO {

    private int order;

    private PlaceDTO place;

    private Itinerary itinerary;

    private Plan plan;

    public ActivityDTO() {
    }

    public int getOrder() {
        return order;
    }

    public ActivityDTO setOrder(int order) {
        this.order = order;
        return this;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public ActivityDTO setPlace(PlaceDTO place) {
        this.place = place;
        return this;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public ActivityDTO setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public Plan getPlan() {
        return plan;
    }

    public ActivityDTO setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }
}
