package com.example.planmyvacation.model.dto;

public class CityDTO {

    private long id;

    private String name;

    private String imageUrl;

    private String country;

    public CityDTO() {
    }

    public long getId() {
        return id;
    }

    public CityDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CityDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getFullName() {
        return name + ", " + country;
    }
}
