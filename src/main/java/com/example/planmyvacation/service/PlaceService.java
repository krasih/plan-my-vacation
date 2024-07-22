package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.Place;

import java.util.List;

public interface PlaceService {

    public List<PlaceDTO> getAll();

    List<PlaceDTO> findAllByLocation(Long id);

    public PlaceDTO getPlaceById(Long id);

    public Place getPlace(Long id);

    public PlaceDTO mapToDTO(Place place);

}
