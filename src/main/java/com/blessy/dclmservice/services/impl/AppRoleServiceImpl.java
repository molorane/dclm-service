package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.AppRole;
import com.blessy.dclmservice.repository.AppRoleRepository;
import com.blessy.dclmservice.services.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleServiceImpl implements AppRoleService {
	
	@Autowired
	AppRoleRepository roleRepo;

	@Override
	public AppRole addRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public AppRole getRole(int role_id) {
		// TODO Auto-generated method stub
		return roleRepo.findById(role_id).get();
	}

}
