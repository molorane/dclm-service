package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.ServiceType;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {

    ServiceType addServiceType(ServiceType serviceType);
    Optional<ServiceType> findByName(String name);
    Optional<ServiceType> findById(Long id);
    List<ServiceType> findAll();
    ServiceType editServiceType(ServiceType serviceType);
}
