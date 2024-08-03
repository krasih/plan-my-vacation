package com.example.planmyvacation.model.dto;

import jakarta.validation.constraints.NotBlank;

public class CareerDTO {

    private Long id;

    @NotBlank(message = "{msg.category.not.empty}")
    private String category;

    @NotBlank(message = "{msg.title.not.empty}")
    private String title;

    @NotBlank(message = "{msg.descr.not.empty}")
    private String description;

    @NotBlank(message = "{msg.category.not.empty}")
    private String published;

    public CareerDTO() {
    }

    public Long getId() {
        return id;
    }

    public CareerDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CareerDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CareerDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CareerDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublished() {
        return published;
    }

    public CareerDTO setPublished(String published) {
        this.published = published;
        return this;
    }
}
