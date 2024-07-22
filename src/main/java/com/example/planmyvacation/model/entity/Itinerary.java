package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "itineraries")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant date;

    private int dayNo;

    @Column(name = "is_last_day")
    private boolean isLastDay;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Activity> activities;

    @ManyToOne(optional = false)
    private Plan plan;


    public Itinerary() {

        activities = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Itinerary setId(long id) {
        this.id = id;
        return this;
    }

    public Instant getDate() {
        return date;
    }

    public Itinerary setDate(Instant date) {
        this.date = date;
        return this;
    }

    public int getDayNo() {
        return dayNo;
    }

    public Itinerary setDayNo(int dayNo) {
        this.dayNo = dayNo;
        return this;
    }

    public boolean isLastDay() {
        return isLastDay;
    }

    public Itinerary setLastDay(boolean lastDay) {
        isLastDay = lastDay;
        return this;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Itinerary setActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public Plan getPlan() {
        return plan;
    }

    public Itinerary setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }
}
