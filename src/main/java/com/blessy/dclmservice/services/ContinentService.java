package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.Continent;

import java.util.List;
import java.util.Optional;


public interface ContinentService {

    Continent addContinent(Continent continent);
    Optional<Continent> findByName(String name);
    Optional<Continent> findById(Long id);
    List<Continent> findAll();
}
