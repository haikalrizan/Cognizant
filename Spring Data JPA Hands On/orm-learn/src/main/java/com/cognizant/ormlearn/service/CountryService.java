package com.cognizant.ormlearn.service;

import java.util.List;
import javax.transaction.Transactional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries(){
		return countryRepository.findAll();
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException{
		Optional<Country> result = countryRepository.findById(countryCode);
		if(!result.isPresent())
			throw new CountryNotFoundException("Country not found!");
		else {
			Country country = result.get();
			return country;
		}
	}
	
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(Country country) {
		Country previousCountry = countryRepository.findById(country.getCode()).get();
		previousCountry.setName(country.getName());
		Country updatedCountry = previousCountry;
		countryRepository.save(updatedCountry);
	}
	
	@Transactional
	public void deleteCountry(String code) {
		countryRepository.deleteById(code);
	}

	public List<Country> findCountryByPartialName(String name){
		return null;
	}
}