package com.blessy.dclmservice.repository;

import com.blessy.dclmservice.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
	
	AppRole findByRole(String role);

}
