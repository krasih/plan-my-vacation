package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//    List<City> findAllByCountry_NameOrderOrderByNameAsc();

    boolean existsCityByName(String name);

}
