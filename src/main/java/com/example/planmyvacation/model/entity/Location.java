package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @ManyToOne(optional = false)
    private Country country;

    @ManyToOne(optional = false)
    private City city;


    public Location() {
    }

    public Location(Country country, City city) {
        this.country = country;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public Location setId(long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public Location setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Location setCountry(Country country) {
        this.country = country;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Location setCity(City city) {
        this.city = city;
        return this;
    }
}
