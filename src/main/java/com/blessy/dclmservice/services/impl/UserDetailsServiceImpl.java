package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.CustomUserDetails;
import com.blessy.dclmservice.model.Role;
import com.blessy.dclmservice.model.User;
import com.blessy.dclmservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);

		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

		toList(optionalUser.get().getRoles());

		return optionalUser.map(users -> new CustomUserDetails(users)).get();

//        if (optionalUser == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        toList(optionalUser.get().getRoles());
//        return new CustomUserDetails(optionalUser.get());
	}

	public void toList(Set<Role> roles) {
		for (Role role : roles) {
			logger.info("Role: " + role.getRole());
		}
	}

}
