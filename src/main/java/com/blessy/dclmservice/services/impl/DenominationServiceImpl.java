package com.blessy.dclmservice.services.impl;

import com.blessy.dclmservice.model.Denomination;
import com.blessy.dclmservice.model.User;
import com.blessy.dclmservice.repository.DenominationRepository;
import com.blessy.dclmservice.services.DenominationService;
import com.blessy.dclmservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DenominationServiceImpl implements DenominationService {

	private final DenominationRepository denominationRepository;
	private final UserService userService;

	@Override
	public Denomination getDenomination(Integer id) {
		// TODO Auto-generated method stub
		return denominationRepository.findById(id).get();
	}

	@Override
	public Denomination updateDenomination(Denomination denomination) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		Denomination updateDenomination = getDenomination(1);
		denomination.setId(updateDenomination.getId());
		updateDenomination.setName(denomination.getName());
		updateDenomination.setSname(denomination.getSname());
		updateDenomination.setCountry(denomination.getCountry());
		updateDenomination.setFounder(denomination.getFounder());
		updateDenomination.setStartDate(denomination.getStartDate());
		updateDenomination.setHq(denomination.getHq());
		updateDenomination.setGpsLat(denomination.getGpsLat());
		updateDenomination.setGpsLong(denomination.getGpsLong());
		updateDenomination.setMission(denomination.getMission());
		updateDenomination.setUser(user);
		return updateDenomination;
	}

}
