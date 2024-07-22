package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByLocationOrderByRatingDesc(Location location);
    List<Place> findAllByLocationId(Long id);
}
