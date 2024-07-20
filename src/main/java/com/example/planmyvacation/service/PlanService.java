package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlanCreateDTO;

import java.util.List;

public interface PlanService {

    void createPlan(PlanCreateDTO planDTO);

}
