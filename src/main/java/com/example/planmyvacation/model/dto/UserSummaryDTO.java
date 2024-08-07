package com.example.planmyvacation.model.dto;

import jakarta.validation.constraints.NotBlank;

public class UserSummaryDTO {

    Long id;

    @NotBlank
    String username;

    @NotBlank
    String email;

    @NotBlank
    String role;

    public UserSummaryDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserSummaryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserSummaryDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSummaryDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserSummaryDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
