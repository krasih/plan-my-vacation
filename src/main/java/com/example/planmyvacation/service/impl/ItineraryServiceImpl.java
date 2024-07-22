package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.ItineraryDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.repository.ItineraryRepository;
import com.example.planmyvacation.service.ActivityService;
import com.example.planmyvacation.service.ItineraryService;
import com.example.planmyvacation.util.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final ActivityService activityService;

    public ItineraryServiceImpl(ItineraryRepository itineraryRepository, ActivityService activityService) {
        this.itineraryRepository = itineraryRepository;
        this.activityService = activityService;
    }


    @Override
    public List<ItineraryDTO> getAll() {

        return itineraryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ItineraryDTO getItineraryById(Long id) {

        return itineraryRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public ItineraryDTO mapToDTO(Itinerary itinerary) {

        LocalDate date = Utils.getLocalDate(itinerary.getDate());
//        List<ActivityDTO> activitiesDTO = activityService.mapAllToDTO(itinerary.getActivities());

        List<ActivityDTO> activitiesDTO = itinerary.getActivities()
                .stream()
                .map(activityService::mapToDTO)
                .toList();

        return new ItineraryDTO()
                .setId(itinerary.getId())
                .setDate(date)
                .setDayNo(itinerary.getDayNo())
                .setLastDay(itinerary.isLastDay())
                .setActivities(activitiesDTO)
                .setPlan(itinerary.getPlan());
    }
}
