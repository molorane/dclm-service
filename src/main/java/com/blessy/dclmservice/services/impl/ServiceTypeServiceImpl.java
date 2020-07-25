package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.ServiceType;
import com.blessy.dclmservice.repository.ServiceTypeRepository;
import com.blessy.dclmservice.services.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Override
    public ServiceType addServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public Optional<ServiceType> findByName(String name) {
        return serviceTypeRepository.findByName(name);
    }

    @Override
    public Optional<ServiceType> findById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    @Override
    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType editServiceType(ServiceType serviceType) {
        ServiceType edited = serviceTypeRepository.findById(serviceType.getId()).get();
        edited.setAbv(serviceType.getAbv());
        edited.setName(serviceType.getName());
        return edited;
    }
}
