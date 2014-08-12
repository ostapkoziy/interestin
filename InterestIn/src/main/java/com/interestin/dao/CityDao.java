package com.interestin.dao;

import java.util.List;

import com.interestin.model.City;

public interface CityDao extends AbstractDao<City, String> {

	List<City> getCountryCities(Long countryId);

	City getByKey(String key, Long countryId);
}
