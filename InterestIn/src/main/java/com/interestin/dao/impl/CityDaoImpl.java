package com.interestin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.interestin.dao.CityDao;
import com.interestin.model.City;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City, String> implements
		CityDao {

	protected CityDaoImpl() {
		super(City.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCountryCities(Long countryId) {
		String hql = "SELECT c FROM City c WHERE c.country.countryId=:countryId AND c.disabled=false";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("countryId", countryId);
		List<City> cities = query.list();
		return cities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public City getByKey(String key, Long countryId) {
		String hql = "SELECT c FROM City c WHERE c.key=:key AND c.country.countryId=:countryId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("key", key);
		query.setParameter("countryId", countryId);
		List<City> cities = query.list();
		if (!cities.isEmpty()) {
			return cities.get(0);
		}
		return null;
	}
}
