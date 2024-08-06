package com.example.planmyvacation.model.convert;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Place;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceDTOConverter {

    private final ModelMapper modelMapper;


    public PlaceDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlaceDTO mapPlaceToPlaceDTO(Place place) {

        return modelMapper.map(place, PlaceDTO.class);
    }

    public Place mapPlaceDTOToPlace(PlaceDTO placeDTO) {

        return modelMapper.map(placeDTO, Place.class);
    }

}
