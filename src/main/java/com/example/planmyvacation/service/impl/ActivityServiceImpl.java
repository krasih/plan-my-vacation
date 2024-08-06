package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.convert.ActivityDTOConverter;
import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.repository.ActivityRepository;
import com.example.planmyvacation.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDTOConverter converter;
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl( ActivityDTOConverter converter, ActivityRepository activityRepository ) {

        this.converter = converter;
        this.activityRepository = activityRepository;
    }


    @Override
    public List<ActivityDTO> getAll() {

        return activityRepository.findAll()
                .stream()
                .map(converter::mapActivityToActivityDTO)
                .toList();
    }

    @Override
    public ActivityDTO getActivityById(Long id) {

        return activityRepository.findById(id)
                .map(converter::mapActivityToActivityDTO)
                .orElse(null);
    }
}
