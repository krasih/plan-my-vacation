package com.example.planmyvacation.model.dto;

public class CityDTO {

    private String name;

    private String country;

    public CityDTO() {
    }

    public String getName() {
        return name;
    }

    public CityDTO setName(String name) {
        this.name = name;
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
