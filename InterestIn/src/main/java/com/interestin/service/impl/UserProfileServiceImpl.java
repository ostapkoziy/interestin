package com.interestin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interestin.dao.UserProfileDao;
import com.interestin.model.UserProfile;
import com.interestin.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao userProfileDao;

	@Override
	public void saveUserProfile(UserProfile userProfile) {
		userProfileDao.saveOrUpdate(userProfile);
	}

}
