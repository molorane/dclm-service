package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.Continent;
import com.blessy.dclmservice.repository.ContinentRepository;
import com.blessy.dclmservice.services.ContinentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService {

    private final ContinentRepository continentRepository;

    @Override
    public Continent addContinent(Continent continent) {
        return continentRepository.save(continent);
    }

    @Override
    public Optional<Continent> findByName(String name) {
        return continentRepository.findByName(name);
    }

    @Override
    public Optional<Continent> findById(Long id) {
        return continentRepository.findById(id);
    }

    @Override
    public List<Continent> findAll() {
        return continentRepository.findAll();
    }
}
