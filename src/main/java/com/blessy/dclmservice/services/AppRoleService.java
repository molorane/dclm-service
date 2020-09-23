package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.AppRole;

public interface AppRoleService {
	AppRole addRole(AppRole role);
	AppRole getRole(int role_id);
}
