package com.example.planmyvacation.model.convert;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.entity.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActivityDTOConverter {

    private final ModelMapper modelMapper;


    public ActivityDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ActivityDTO mapActivityToActivityDTO(Activity activity) {

        return modelMapper.map(activity, ActivityDTO.class);
    }

    public Activity mapActivityDTOToActivity(ActivityDTO activityDTO) {

        return modelMapper.map(activityDTO, Activity.class);
    }

}
