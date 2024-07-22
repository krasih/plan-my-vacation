package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.City;
import com.example.planmyvacation.model.entity.Place;
import com.example.planmyvacation.repository.PlaceRepository;
import com.example.planmyvacation.service.CityService;
import com.example.planmyvacation.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    public List<PlaceDTO> getAll() {

        return placeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PlaceDTO getPlaceById(Long id) {

        return placeRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public PlaceDTO mapToDTO(Place place) {

        return new PlaceDTO()
                .setName(place.getName())
                .setType(place.getType())
                .setCategories(place.getCategories())
                .setDescription(place.getDescription())
                .setRating(place.getRating())
                .setLocation(place.getLocation())
                .setImageUrl(place.getImageUrl());
    }
}
