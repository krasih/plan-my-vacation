package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Place;
import com.example.planmyvacation.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Modifying
    @Query( value = "DELETE FROM my_places WHERE plan_id = :placeId AND place_id = :planId ",
            nativeQuery = true )
    void deletePlace(@Param("placeId") Long placeId, @Param("planId") Long planId);

    @Modifying
    @Query( value = "INSERT INTO my_places (plan_id, place_id) VALUES (:planId, :placeId) ",
            nativeQuery = true )
    void addPlace(@Param("placeId") Long placeId, @Param("planId") Long planId);
}
