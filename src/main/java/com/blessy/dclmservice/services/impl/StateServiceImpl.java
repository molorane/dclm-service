package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.State;
import com.blessy.dclmservice.repository.StateRepository;
import com.blessy.dclmservice.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public State addState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public Optional<State> findByName(String name) {
        return stateRepository.findByName(name);
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<State> findById(Long id) {
        return stateRepository.findById(id);
    }
}
