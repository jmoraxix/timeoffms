package com.timeoffms.web.service;

import com.timeoffms.web.dao.CountryRepository;
import com.timeoffms.web.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll(){
		return countryRepository.findAll();
	}

	public Country findCountry(String code){
		return countryRepository.findByCode(code);
	}

	public void saveCountry(Country country){
		countryRepository.save(country);
	}

	public void deleteCountry(String code){
		countryRepository.deleteByCode(code);
	}
}
