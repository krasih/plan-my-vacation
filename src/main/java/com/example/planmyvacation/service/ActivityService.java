package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.entity.Activity;

import java.util.List;

public interface ActivityService {

    public List<ActivityDTO> getAll();

    public ActivityDTO getActivityById(Long id);

//    public ActivityDTO mapToDTO(Activity activity);

}
