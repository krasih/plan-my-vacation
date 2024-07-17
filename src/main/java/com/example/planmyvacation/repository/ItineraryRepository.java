package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Country;
import com.example.planmyvacation.model.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
