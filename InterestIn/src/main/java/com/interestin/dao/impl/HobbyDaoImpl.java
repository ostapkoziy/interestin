package com.interestin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.interestin.dao.HobbyDao;
import com.interestin.model.Hobby;

@Repository
public class HobbyDaoImpl extends AbstractDaoImpl<Hobby, String> implements
		HobbyDao {

	protected HobbyDaoImpl() {
		super(Hobby.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hobby> getParentHobbies() {
		String hql = "SELECT h FROM Hobby h WHERE h.category IS NULL AND h.disabled=false";
		Query query = getCurrentSession().createQuery(hql);
		List<Hobby> hobbies = query.list();
		return hobbies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Hobby getByKey(String key) {
		String hql = "SELECT h FROM Hobby h WHERE h.key=:key";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("key", key);
		List<Hobby> hobbies = query.list();
		if (!hobbies.isEmpty()) {
			return hobbies.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getHobbyCategories(Long hobbyId) {
		String hql = "SELECT DISTINCT h.category FROM Hobby h WHERE h.parent.hobbyId=:hobbyId AND h.disabled=false";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("hobbyId", hobbyId);
		List<String> categories = query.list();
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hobby> getCategoryHobbies(Long hobbyId, String category) {
		String hql = "SELECT h FROM Hobby h WHERE h.category=:category AND h.parent.hobbyId=:hobbyId AND h.disabled=false";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("hobbyId", hobbyId);
		query.setParameter("category", category);
		List<Hobby> hobbies = query.list();
		return hobbies;
	}

}
