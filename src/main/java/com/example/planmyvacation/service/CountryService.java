package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.CountryDTO;

import java.util.List;
import java.util.Map;

public interface CountryService {

    public List<CountryDTO> getAll();

    public Map<String, List<CityDTO>> getAllAsMap();

}
