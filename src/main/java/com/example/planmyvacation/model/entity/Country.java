package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<City> cities;


    public Country() {
        cities = new ArrayList<>();
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, String... cities) {
        this.name = name;
        this.cities = Arrays.stream(cities)
                .map(City::new)
                .toList();
    }

    public long getId() {
        return id;
    }

    public Country setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public Country setCities(List<City> cities) {
        this.cities = cities;
        return this;
    }
}
