package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.Category;
import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.enums.PlaceType;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaceDTO {

    private long id;

    private double rating;

    private String name;

    private String description;

    private String imageUrl;

    private PlaceType type;

    private Set<Category> categories;

    private Location location;

    public PlaceDTO() {
    }

    public long getId() {
        return id;
    }

    public PlaceDTO setId(long id) {
        this.id = id;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public PlaceDTO setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlaceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PlaceDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public PlaceDTO setType(PlaceType type) {
        this.type = type;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public PlaceDTO setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public PlaceDTO setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getCategoriesAsString() {

        return categories.stream()
                .map(category -> category.getCategory())
                .collect(Collectors.joining(", "));
    }
}
