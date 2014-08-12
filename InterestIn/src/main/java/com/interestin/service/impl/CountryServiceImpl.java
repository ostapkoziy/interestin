package com.interestin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interestin.dao.CityDao;
import com.interestin.dao.CountryDao;
import com.interestin.model.City;
import com.interestin.model.Country;
import com.interestin.model.xml.Countries;
import com.interestin.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryDao countryDao;
	@Autowired
	CityDao cityDao;

	@Override
	public void saveCountries(Countries countries) {
		for (Country country : countries.getCountries()) {
			Country dbCountry = countryDao.getByKey(country.getKey());
			if (dbCountry != null) {
				dbCountry.setDisabled(country.getDisabled());
				dbCountry.setCities(country.getCities());
				country = dbCountry;
			}
			countryDao.saveOrUpdate(country);
			for (City city : country.getCities()) {
				City dbCity = cityDao.getByKey(city.getKey(),
						country.getCountryId());
				if (dbCity != null) {
					dbCity.setDisabled(city.getDisabled());
					city = dbCity;
				}
				city.setCountry(country);
				cityDao.saveOrUpdate(city);
			}
		}
	}

	@Override
	public List<Country> getAllCountries() {
		return countryDao.getAllCountries();
	}

	@Override
	public List<City> getCountryCities(Long countryId) {
		return cityDao.getCountryCities(countryId);
	}

}
