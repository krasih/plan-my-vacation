package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
