package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	public static void main(String[] args) throws CountryNotFoundException {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
		
		LOGGER.info("Inside main");
	}

	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		
		LOGGER.debug("countries=()", countries);
		LOGGER.info("End");
	}
	

	private static void getAllCountriesTest() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	

	private static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country newCountry = new Country("NC", "New Country");
		countryService.addCountry(newCountry);
		
		Country country = countryService.findCountryByCode("NC");
		
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	

	private static void testUpdateCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country updateCountry = new Country("NC", "New UpdatedCountry");
		countryService.updateCountry(updateCountry);
		
		Country country = countryService.findCountryByCode("NC");
		
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	

	private static void testDeleteCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country newCountry = new Country("DC", "Deleted Country");
		countryService.addCountry(newCountry);
		
		countryService.deleteCountry("DC");
		Country deletedCountry = countryService.findCountryByCode("DC");
		
		LOGGER.debug("Country:{}", deletedCountry);
		LOGGER.info("End");
	}
	
	
}