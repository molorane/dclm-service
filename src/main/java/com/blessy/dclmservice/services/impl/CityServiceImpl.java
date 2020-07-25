package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.City;
import com.blessy.dclmservice.repository.CityRepository;
import com.blessy.dclmservice.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
