package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.CountryDTO;
import com.example.planmyvacation.model.entity.Country;
import com.example.planmyvacation.repository.CountryRepository;
import com.example.planmyvacation.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<CountryDTO> getAll() {

        return countryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public Map<String, List<CityDTO>> getAllAsMap() {

        Map<String, List<CityDTO>> allCountriesAndCities = new LinkedHashMap<>();

        List<Country> allCountries = countryRepository.findAll();

        for (Country country : allCountries) {

            String countryName = country.getName();

            List<CityDTO> cities = country.getCities().stream()
                    .map(city -> new CityDTO()
                            .setName(city.getName())
                            .setCountry(city.getCountry().getName()))
                    .toList();

            allCountriesAndCities.putIfAbsent(countryName, new ArrayList<>());
            allCountriesAndCities.get(countryName).addAll(cities);
        }

        return allCountriesAndCities;
    }

    private CountryDTO mapToDTO(Country country) {

        return new CountryDTO()
                .setName(country.getName())
                .setCities(country.getCities());
    }
}
