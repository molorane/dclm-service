package com.blessy.dclmservice.repository;

import com.blessy.dclmservice.model.Denomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominationRepository extends JpaRepository<Denomination, Integer> {

}
