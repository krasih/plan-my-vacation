package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
