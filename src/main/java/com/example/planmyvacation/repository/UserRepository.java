package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

}
