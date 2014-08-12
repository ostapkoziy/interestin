package com.interestin.dao.impl;

import com.interestin.dao.UserDao;
import com.interestin.model.User;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Danylo
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements
		UserDao {
	protected UserDaoImpl() {
		super(User.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "SELECT u FROM User u";
		Query query = getCurrentSession().createQuery(hql);
		List<User> users = query.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(String email) {
		String hql = "SELECT u FROM User u WHERE u.email=:email";
		Query query = getCurrentSession().createQuery(hql);
		query.setString("email", email);
		List<User> users = query.list();
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
}
