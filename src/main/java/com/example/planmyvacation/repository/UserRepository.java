package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
