package com.interestin.service;

import java.util.List;

import com.interestin.model.City;
import com.interestin.model.Country;
import com.interestin.model.xml.Countries;

public interface CountryService {

	void saveCountries(Countries countries);

	List<Country> getAllCountries();

	List<City> getCountryCities(Long countryId);
}
