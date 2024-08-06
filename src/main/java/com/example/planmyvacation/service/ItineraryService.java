package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.ItineraryDTO;
import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Itinerary;

import java.util.List;

public interface ItineraryService {

    public List<ItineraryDTO> getAll();

    public ItineraryDTO getItineraryById(Long id);

//    public ItineraryDTO mapToDTO(Itinerary itinerary);

}
