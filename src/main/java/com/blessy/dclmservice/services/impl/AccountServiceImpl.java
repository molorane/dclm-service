package com.blessy.dclmservice.services.impl;


import com.blessy.dclmservice.model.AppRole;
import com.blessy.dclmservice.model.Account;
import com.blessy.dclmservice.repository.AppRoleRepository;
import com.blessy.dclmservice.repository.AccountRepository;
import com.blessy.dclmservice.services.AccountService;
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
public class AccountServiceImpl implements AccountService {
	
	 @Autowired
	 private AccountRepository accountRepository;
	 
	 @Autowired
	 private AppRoleRepository roleRepository;

	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	public Account addAccount(Account account) {
		// TODO Auto-generated method stub
		AppRole role = roleRepository.findByRole("SITE_USER");
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setRoles(new HashSet<AppRole>(Arrays.asList(role)));
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> findByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.findByUsername(username);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account getCurrentAccount() {
		Account user = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return accountRepository.findByUsername(user.getUsername()).get();
	}

}
