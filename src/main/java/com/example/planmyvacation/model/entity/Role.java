package com.example.planmyvacation.model.entity;

import com.example.planmyvacation.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public Role() {
    }

    public Role(UserRole role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public Role setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
