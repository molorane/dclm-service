package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Country addCountry(Country continent);
    Optional<Country> findByName(String name);
    List<Country> findAll();
    Optional<Country> findById(Long id);
    List<Object> geAllCountries(int continent_id);
}
