package com.interestin.dao.impl;

import org.springframework.stereotype.Repository;

import com.interestin.dao.UserProfileDao;
import com.interestin.model.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile, String>
		implements UserProfileDao {

	protected UserProfileDaoImpl() {
		super(UserProfile.class);
	}

}
