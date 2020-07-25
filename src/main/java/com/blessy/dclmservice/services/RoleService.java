package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.Role;

public interface RoleService {
	Role addRole(Role role);
	Role getRole(int role_id);
}
