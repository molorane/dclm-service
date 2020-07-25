package com.blessy.dclmservice.repository;

import com.blessy.dclmservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);
    List<Country> findAll();

    @Query(value = "SELECT  ct.id,ct.name as continent_name, co.name as country_ame \n" +
            "FROM country co \n" +
            "JOIN continent ct \n" +
            "ON co.continent_id = ct.id \n" +
            "WHERE co.continent_id = :continent_id",nativeQuery = true)
    List<Object> geAllCountries(@Param("continent_id") int continent_id);

}
