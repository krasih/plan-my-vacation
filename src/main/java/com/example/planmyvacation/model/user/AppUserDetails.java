package com.example.planmyvacation.model.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUserDetails extends User {

    private final String email;

    public AppUserDetails(
            String username,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
