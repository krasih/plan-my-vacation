package com.example.planmyvacation.model.convert;

import com.example.planmyvacation.model.dto.*;
import com.example.planmyvacation.model.entity.Activity;
import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PlanDTOConverter {

    private final ModelMapper modelMapper;
    private final PlaceDTOConverter placeDTOConverter;
    private final ItineraryDTOConverter itineraryDTOConverter;


    public PlanDTOConverter(
            ModelMapper modelMapper,
            PlaceDTOConverter placeDTOConverter,
            ItineraryDTOConverter itineraryDTOConverter
    ) {

        this.modelMapper = modelMapper;
        this.placeDTOConverter = placeDTOConverter;
        this.itineraryDTOConverter = itineraryDTOConverter;
    }

    public PlanDetailsDTO mapPlanToPlanDetailsDTO(Plan plan) {

        PlanDetailsDTO planDetailsDTO = modelMapper.map(plan, PlanDetailsDTO.class);

        LocalDate startDate = Utils.getLocalDate(plan.getStartDate());
        LocalDate endDate = Utils.getLocalDate(plan.getEndDate());

        List<PlaceDTO> myPlaces = plan.getMyPlaces().stream()
                .map(placeDTOConverter::mapPlaceToPlaceDTO)
                .toList();

        List<ItineraryDTO> itineraries = plan.getItineraries()
                .stream()
                .map(itineraryDTOConverter::mapItineraryToItineraryDTO)
                .toList();

        return planDetailsDTO
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setMyPlaces(myPlaces)
                .setItineraries(itineraries);
    }

    public PlanSummaryDTO mapPlanToPlanSummaryDTO(Plan plan) {

        PlanSummaryDTO planSummaryDTO = modelMapper.map(plan, PlanSummaryDTO.class);

        LocalDate startDate = Utils.getLocalDate(plan.getStartDate());
        LocalDate endDate = Utils.getLocalDate(plan.getEndDate());

        return planSummaryDTO
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setCity(plan.getLocation().getCity().getName())
                .setCountry(plan.getLocation().getCountry().getName());
    }

}
