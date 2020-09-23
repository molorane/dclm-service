package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.CustomUserDetails;
import com.blessy.dclmservice.model.AppRole;
import com.blessy.dclmservice.model.Account;
import com.blessy.dclmservice.repository.AccountRepository;
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

	private final AccountRepository accountRepository;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> accountOptional = accountRepository.findByUsername(username);

		accountOptional.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

		toList(accountOptional.get().getRoles());

		return accountOptional.map(CustomUserDetails::new).get();

//        if (optionalUser == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        toList(optionalUser.get().getRoles());
//        return new CustomUserDetails(optionalUser.get());
	}

	public void toList(Set<AppRole> roles) {
		for (AppRole role : roles) {
			logger.info("Role: " + role.getRole());
		}
	}

}
