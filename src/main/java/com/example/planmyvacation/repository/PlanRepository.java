package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Place;
import com.example.planmyvacation.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Modifying
    @Query(
            value = "DELETE FROM my_places WHERE plan_id = ?1 AND place_id = ?2 ",
            nativeQuery = true)
    void deletePlace(Long placeId, Long planId);
}
