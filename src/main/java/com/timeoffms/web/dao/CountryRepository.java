package com.timeoffms.web.dao;

import com.timeoffms.web.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByCode(String code);

	@Transactional
	void deleteByCode(String code);
}
