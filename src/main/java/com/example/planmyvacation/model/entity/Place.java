package com.example.planmyvacation.model.entity;

import com.example.planmyvacation.model.enums.PlaceType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    @ManyToMany                                // make fetch = fetchType.EAGER  ???
    private Set<Category> category;

    @ManyToOne(optional=false)
    private Location location;


    public Place() {
        category = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public Place setId(long id) {
        this.id = id;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Place setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Place setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Place setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public Place setType(PlaceType type) {
        this.type = type;
        return this;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public Place setCategory(Set<Category> category) {
        this.category = category;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Place setLocation(Location location) {
        this.location = location;
        return this;
    }
}
