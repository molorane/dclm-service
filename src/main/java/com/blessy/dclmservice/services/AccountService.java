package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

	Account addAccount(Account user);
	Optional<Account> findByUsername(String username);
	Optional<Account> findByEmail(String email);
	List<Account> findAll();
	Account getCurrentAccount();
	 
}
