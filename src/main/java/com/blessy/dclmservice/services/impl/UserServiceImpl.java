package com.blessy.dclmservice.services.impl;


import com.blessy.dclmservice.model.Role;
import com.blessy.dclmservice.model.User;
import com.blessy.dclmservice.repository.RoleRepository;
import com.blessy.dclmservice.repository.UserRepository;
import com.blessy.dclmservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;

	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findByRole("SITE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findByUsername(user.getUsername()).get();
	}

}
