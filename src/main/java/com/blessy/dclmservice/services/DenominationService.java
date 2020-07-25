package com.blessy.dclmservice.services;


import com.blessy.dclmservice.model.Denomination;

public interface DenominationService {
	
	Denomination getDenomination(Integer id);
	Denomination updateDenomination(Denomination denomination);

}
