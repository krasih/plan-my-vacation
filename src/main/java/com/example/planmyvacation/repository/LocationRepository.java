package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByCity_Name(String city);

}
