package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.model.enums.UserRole;
import com.example.planmyvacation.model.user.AppUserDetails;
import com.example.planmyvacation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    private static final String EXISTING_USER = "existingUser";
    private static final String NOT_EXISTING_USER = "notExistingUser";

    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockedUserRepository;

    @BeforeEach
    void setUp() {

        toTest = new AppUserDetailsService(mockedUserRepository);
    }

    @Test
    void loadUserByUsername_If_UserFound() {

        // Arrange
        User testUser = new User()
                .setUsername(EXISTING_USER)
                .setPassword("password")
                .setEmail("existing@email.com")
                .setRole( new Role(UserRole.ADMIN) );

        when(mockedUserRepository.findByUsername(EXISTING_USER))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = toTest.loadUserByUsername(EXISTING_USER);

        // Assert
        Assertions.assertInstanceOf(AppUserDetails.class, userDetails);

        AppUserDetails appUserDetails = (AppUserDetails) userDetails;

        Assertions.assertEquals(EXISTING_USER, appUserDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), appUserDetails.getPassword());
        Assertions.assertEquals(testUser.getEmail(), appUserDetails.getEmail());

        var expectedRole = "ROLE_" + testUser.getRole().getRole().name();
        var actualRole = appUserDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().get();

        Assertions.assertEquals(expectedRole, actualRole);
    }

    @Test
    void loadUserByUsername_If_UserNotFound() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USER)
        );
    }
}