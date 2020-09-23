package com.blessy.dclmservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Account account;

	public CustomUserDetails(final Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getRoles()
			.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
			.collect(Collectors.toList());
		
//      OR	
//		Set<GrantedAuthority> authorities = new HashSet<>();
//		user.getRoles().forEach(role -> {
//			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRole_name());
//			authorities.add(authority);
//		});
//		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !account.accountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !account.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return account.isActive();
	}
	
	public Account getAccount() {
		return account;
	}

}
