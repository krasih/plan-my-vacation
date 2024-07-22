package com.example.planmyvacation.model.dto;

import com.example.planmyvacation.model.entity.City;

import java.util.List;

public class CountryDTO {

    private long id;

    private String name;

    private List<City> cities;

    public CountryDTO() {
    }

    public long getId() {
        return id;
    }

    public CountryDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public CountryDTO setCities(List<City> cities) {
        this.cities = cities;
        return this;
    }
}
