package com.example.planmyvacation.model.convert;

import com.example.planmyvacation.model.dto.ActivityDTO;
import com.example.planmyvacation.model.dto.ItineraryDTO;
import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ItineraryDTOConverter {

    private final ModelMapper modelMapper;
    private final ActivityDTOConverter activityDTOConverter;

    public ItineraryDTOConverter(ModelMapper modelMapper, ActivityDTOConverter activityDTOConverter) {
        this.modelMapper = modelMapper;
        this.activityDTOConverter = activityDTOConverter;
    }

    public ItineraryDTO mapItineraryToItineraryDTO(Itinerary itinerary) {

        ItineraryDTO dto = modelMapper.map(itinerary, ItineraryDTO.class);

        LocalDate date = Utils.getLocalDate(itinerary.getDate());

        List<ActivityDTO> activitiesDTO = itinerary.getActivities()
                .stream()
                .map(activityDTOConverter::mapActivityToActivityDTO)
                .toList();

        return dto.setDate(date).setActivities(activitiesDTO);
    }

    public Itinerary mapItineraryDTOToItinerary(ItineraryDTO itineraryDTO) {

        return modelMapper.map(itineraryDTO, Itinerary.class);
    }

}
