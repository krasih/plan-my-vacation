package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.entity.City;
import com.example.planmyvacation.repository.CityRepository;
import com.example.planmyvacation.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<CityDTO> getAll() {

        return cityRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private CityDTO mapToDTO(City city) {

        return new CityDTO()
                .setId(city.getId())
                .setName(city.getName())
                .setImageUrl(city.getImageUrl())
                .setCountry(city.getCountry().getName());
    }
}
