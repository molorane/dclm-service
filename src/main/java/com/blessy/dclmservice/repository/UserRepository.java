package com.blessy.dclmservice.repository;

import com.blessy.dclmservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	

}
