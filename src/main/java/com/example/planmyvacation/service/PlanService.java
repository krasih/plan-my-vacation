package com.example.planmyvacation.service;

import com.example.planmyvacation.model.PlansSummaryPages;
import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.dto.PlanDetailsDTO;
import com.example.planmyvacation.model.dto.PlanSummaryDTO;

import java.util.List;

public interface PlanService {

    Long createPlan(PlanCreateDTO planDTO);

    List<PlanSummaryDTO> getAll(int pageNo, int pageSize);

    PlansSummaryPages getAll(int page, int pageSize, String status, String sortBy, String sortOrder);

    PlanDetailsDTO getPlanById(long id);

    void deleteMyPlace(Long placeId, Long planId);

    void addMyPlace(Long placeId, Long planId);

    void addItineraryActivity(Long planId, Long itineraryId, Long placeId);

}
