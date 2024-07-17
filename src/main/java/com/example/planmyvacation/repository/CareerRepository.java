package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
