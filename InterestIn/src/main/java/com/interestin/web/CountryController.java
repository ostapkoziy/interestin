package com.interestin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interestin.model.City;
import com.interestin.model.Country;
import com.interestin.service.CountryService;

@Controller
@RequestMapping("/countries")
public class CountryController {
	@Autowired
	CountryService countryService;

	@RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getAllCountries() {
		List<Country> countries = countryService.getAllCountries();
		return new ResponseEntity<>(countries, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{countryId}/cities", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getAllCities(@PathVariable Long countryId) {
		List<City> cities = countryService.getCountryCities(countryId);
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
}
