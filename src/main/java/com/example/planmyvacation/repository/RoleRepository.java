package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
