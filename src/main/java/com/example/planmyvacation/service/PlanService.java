package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.ItineraryDTO;
import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.dto.PlanDetailsDTO;

import java.util.List;

public interface PlanService {

    void createPlan(PlanCreateDTO planDTO);

    public List<PlanDetailsDTO> getAll();

    PlanDetailsDTO getPlanById(long id);




//    TODO: Implement the following methods
//    void deletePlan(long planId);
//    List<PlanSummaryDTO> getAllPlansSummary();


}
