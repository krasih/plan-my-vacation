package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.convert.ItineraryDTOConverter;
import com.example.planmyvacation.model.dto.ItineraryDTO;
import com.example.planmyvacation.repository.ItineraryRepository;
import com.example.planmyvacation.service.ItineraryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryDTOConverter converter;
    private final ItineraryRepository itineraryRepository;

    public ItineraryServiceImpl( ItineraryDTOConverter converter, ItineraryRepository itineraryRepository ) {

        this.converter = converter;
        this.itineraryRepository = itineraryRepository;
    }


    @Override
    public List<ItineraryDTO> getAll() {

        return itineraryRepository.findAll()
                .stream()
                .map(converter::mapItineraryToItineraryDTO)
                .toList();
    }

    @Override
    public ItineraryDTO getItineraryById(Long id) {

        return itineraryRepository.findById(id)
                .map(converter::mapItineraryToItineraryDTO)
                .orElse(null);
    }
}
