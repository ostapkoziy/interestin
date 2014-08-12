package com.interestin.dao;

import java.util.List;

import com.interestin.model.Country;

public interface CountryDao extends AbstractDao<Country, String> {
	List<Country> getAllCountries();

	Country getByKey(String key);

}
