package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.CareerDTO;

import java.util.List;

public interface CareerService {
    List<CareerDTO> findAll();

    CareerDTO findById(long id);

    CareerDTO create(CareerDTO careerDTO);

    CareerDTO update(Long id, CareerDTO careerDTO);

    void delete(Long id);
}
