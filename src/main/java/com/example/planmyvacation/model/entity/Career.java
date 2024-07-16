package com.example.planmyvacation.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String published;


    public Career() {
    }

    public long getId() {
        return id;
    }

    public Career setId(long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Career setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Career setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Career setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublished() {
        return published;
    }

    public Career setPublished(String published) {
        this.published = published;
        return this;
    }
}
