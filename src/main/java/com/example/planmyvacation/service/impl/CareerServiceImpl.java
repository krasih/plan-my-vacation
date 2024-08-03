package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.CareerDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CareerServiceImpl implements com.example.planmyvacation.service.CareerService {

    private final RestClient restClient;

    public CareerServiceImpl() {

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Override
    public List<CareerDTO> findAll() {

        return restClient.get()
                .uri("/careers")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public CareerDTO findById(long id) {

        return restClient.get()
                .uri("/careers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body( CareerDTO.class );
    }

    @Override
    public CareerDTO create(CareerDTO careerDTO) {

        return restClient.post()
                .uri("/careers")
                .contentType(MediaType.APPLICATION_JSON)
                .body(careerDTO)
                .retrieve()
                .body( CareerDTO.class );
    }

    @Override
    public CareerDTO update(Long id, CareerDTO careerDTO) {

        return restClient.put()
                .uri("/careers/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(careerDTO)
                .retrieve()
                .body( CareerDTO.class );
    }

    @Override
    public void delete(Long id) {

        restClient.delete()
                .uri("/careers/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
