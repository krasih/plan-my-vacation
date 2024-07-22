package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_num")
    private int order;

    @ManyToOne(optional = false)
    private Place place;

    @ManyToOne()
    private Itinerary itinerary;

    @ManyToOne(optional = false)
    private Plan plan;


    public Activity() {
    }

    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public Activity setOrder(int order) {
        this.order = order;
        return this;
    }

    public Place getPlace() {
        return place;
    }

    public Activity setPlace(Place place) {
        this.place = place;
        return this;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public Activity setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public Plan getPlan() {
        return plan;
    }

    public Activity setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }
}
