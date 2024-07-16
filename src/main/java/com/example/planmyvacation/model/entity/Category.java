package com.example.planmyvacation.model.entity;

import com.example.planmyvacation.model.enums.PlaceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String category;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;


    public Category() {
    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Category setCategory(String category) {
        this.category = category;
        return this;
    }

    public PlaceType getLocationType() {
        return placeType;
    }

    public Category setLocationType(PlaceType placeType) {
        this.placeType = placeType;
        return this;
    }
}
