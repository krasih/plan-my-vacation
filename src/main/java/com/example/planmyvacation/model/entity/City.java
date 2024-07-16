package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;


    public City() {
    }

    public long getId() {
        return id;
    }

    public City setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }
}
