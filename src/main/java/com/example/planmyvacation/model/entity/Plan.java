package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "start_date")
    private Instant startDate;

    @Column(nullable = false, name = "end_date")
    private Instant endDate;

    private boolean active;

    @ManyToOne(optional = false)
    private Location location;

    @OneToOne(optional = false, orphanRemoval=true, cascade = CascadeType.ALL)
    private Itinerary myPlaces;

    @OneToMany(mappedBy = "plan", orphanRemoval=true, cascade = CascadeType.ALL)
    private List<Itinerary> itineraries;

    @ManyToOne(optional = false)
    private User user;


    public Plan() {
        itineraries = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Plan setId(long id) {
        this.id = id;
        return this;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Plan setStartDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Plan setEndDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Plan setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Plan setLocation(Location location) {
        this.location = location;
        return this;
    }

    public List<Itinerary> getDayPlans() {
        return itineraries;
    }

    public Plan setDayPlans(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Plan setUser(User user) {
        this.user = user;
        return this;
    }
}
