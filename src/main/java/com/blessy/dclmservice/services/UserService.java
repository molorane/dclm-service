package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	User addUser(User user);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);	
	List<User> findAll();
	User getCurrentUser();
	 
}
