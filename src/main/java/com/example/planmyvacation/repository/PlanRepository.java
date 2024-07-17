package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Place;
import com.example.planmyvacation.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
