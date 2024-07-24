package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.dto.PlanDetailsDTO;

import java.util.List;

public interface PlanService {

    Long createPlan(PlanCreateDTO planDTO);

    public List<PlanDetailsDTO> getAll();

    PlanDetailsDTO getPlanById(long id);

    void deleteMyPlace(Long placeId, Long planId);

    void addMyPlace(Long placeId, Long planId);

    void addItineraryActivity(Long planId, Long itineraryId, Long placeId);


//    TODO: Implement the following methods
//    void deletePlan(long planId);
//    List<PlanSummaryDTO> getAllPlansSummary();


}
