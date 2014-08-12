package com.interestin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.interestin.dao.CountryDao;
import com.interestin.model.Country;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<Country, String> implements
		CountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getAllCountries() {
		String hql = "SELECT c FROM Country c WHERE c.disabled=false";
		Query query = getCurrentSession().createQuery(hql);
		List<Country> countries = query.list();
		return countries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Country getByKey(String key) {
		String hql = "SELECT c FROM Country c WHERE c.key=:key";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("key", key);
		List<Country> countries = query.list();
		if (!countries.isEmpty()) {
			return countries.get(0);
		}
		return null;
	}

}
