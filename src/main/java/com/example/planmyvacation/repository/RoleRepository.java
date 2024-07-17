package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);

    Role getByRole(UserRole role);
}
