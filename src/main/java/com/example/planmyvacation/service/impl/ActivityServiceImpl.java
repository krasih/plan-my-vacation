package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.repository.ActivityRepository;
import com.example.planmyvacation.service.ActivityService;
import com.example.planmyvacation.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final PlaceService placeService;

    public ActivityServiceImpl(ActivityRepository activityRepository, PlaceService placeService) {
        this.activityRepository = activityRepository;
        this.placeService = placeService;
    }


    @Override
    public List<ActivityDTO> getAll() {

        return activityRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ActivityDTO getActivityById(Long id) {

        return activityRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public ActivityDTO mapToDTO(Activity activity) {

        PlaceDTO placeDTO = placeService.mapToDTO(activity.getPlace());

        return new ActivityDTO()
                .setId(activity.getId())
                .setOrder(activity.getOrder())
                .setPlace(placeDTO)
                .setItinerary(activity.getItinerary())
                .setPlan(activity.getPlan());
    }
}
