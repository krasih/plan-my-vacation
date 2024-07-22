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

    @ManyToMany/*(cascade = CascadeType.ALL)*/
    @JoinTable(
            name = "my_places",
            joinColumns = { @JoinColumn(name = "plan_id") },
            inverseJoinColumns = { @JoinColumn(name = "place_id") }
    )
    private List<Place> myPlaces;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Itinerary> itineraries;

    @ManyToOne(optional = false)
    private User user;


    public Plan() {

        this.myPlaces = new ArrayList<>();
        this.itineraries = new ArrayList<>();
    }

    public Plan(Instant startDate, Instant endDate, Location location, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.user = user;
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

    public List<Place> getMyPlaces() {
        return myPlaces;
    }

    public Plan setMyPlaces(List<Place> myPlaces) {
        this.myPlaces = myPlaces;
        return this;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public Plan setItineraries(List<Itinerary> itineraries) {
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
