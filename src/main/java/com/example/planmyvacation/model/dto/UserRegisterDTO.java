package com.example.planmyvacation.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {

    private long id;

    @NotNull(message = "{msg.username.length}")
    @Size(message = "{msg.username.length}", min = 4, max = 20)
    private String username;

    @NotBlank(message = "{msg.email.length}")
    @Email(message = "{msg.email.not.valid}")
    private String email;

    @NotNull(message = "{msg.password.length}")
    @Size(message = "{msg.password.length}", min = 4, max = 20)
    private String password;

    private String confirmPassword;

    public UserRegisterDTO() {}

    public long getId() {
        return id;
    }

    public UserRegisterDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
