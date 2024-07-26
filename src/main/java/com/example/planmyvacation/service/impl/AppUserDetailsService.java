package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.model.user.AppUserDetails;
import com.example.planmyvacation.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(AppUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(User user) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(map(user.getRole()));

        return new AppUserDetails(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }

    private static GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.getRole()
        );
    }
}
