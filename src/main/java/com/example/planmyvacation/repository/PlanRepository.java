package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {

    Page<Plan> findAllByActive(Pageable pageable, boolean isActive);

    @Modifying
    @Query( value = "DELETE FROM my_places WHERE plan_id = :placeId AND place_id = :planId ",
            nativeQuery = true )
    void deleteMyPlace(@Param("placeId") Long placeId, @Param("planId") Long planId);

    @Modifying
    @Query( value = "INSERT INTO my_places (plan_id, place_id) VALUES (:planId, :placeId) ",
            nativeQuery = true )
    void addMyPlace(@Param("placeId") Long placeId, @Param("planId") Long planId);

    @Modifying
    @Query( value = """
                       INSERT INTO activities (order_num, place_id, itinerary_id, plan_id)
                       SELECT ifnull(max(order_num), 0) + 1, :placeId, :itineraryId, :planId
                       FROM activities WHERE itinerary_id = :itineraryId
                       """,
            nativeQuery = true )
    void addItineraryActivity(@Param("placeId") Long placeId, @Param("itineraryId") Long itineraryId, @Param("planId") Long planId);
}
