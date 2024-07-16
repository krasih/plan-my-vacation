package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;


    public Country() {
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
}
