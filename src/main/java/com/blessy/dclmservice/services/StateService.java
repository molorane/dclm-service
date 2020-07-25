package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.State;

import java.util.List;
import java.util.Optional;

public interface StateService {

    State addState(State state);
    Optional<State> findByName(String name);
    List<State> findAll();
    Optional<State> findById(Long id);
}
