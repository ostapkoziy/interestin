package com.interestin.service.impl;

import com.interestin.dao.UserActivationDao;
import com.interestin.dao.UserDao;
import com.interestin.exception.UserActivationNotFoundException;
import com.interestin.model.User;
import com.interestin.model.UserActivation;
import com.interestin.service.MailService;
import com.interestin.service.UserActivationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * Created by Danylo on 30.01.14.
 */
@Service
public class UserActivationServiceImpl implements UserActivationService {
	private static final Logger log = Logger
			.getLogger(UserActivationServiceImpl.class);
	@Autowired
	UserDao userDao;
	@Autowired
	UserActivationDao userActivationDao;
	@Autowired
	MailService mailService;

	@Override
	public void prepareActivationUser(User user,
			HttpServletRequest httpServletRequest) {
		try {
			log.info("activateUser with email " + user.getEmail());
			String randomToken = saveUserActivation(user);
			String activationUrl = formActivationUrl(httpServletRequest,
					randomToken);
			Locale locale = LocaleContextHolder.getLocale();
			mailService.sendUserActivationMail(user.getEmail(), activationUrl,
					locale);
		} catch (NoSuchAlgorithmException e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void activateUser(String activationKey)
			throws UserActivationNotFoundException {
		log.info("activateUser with activationKey " + activationKey);
		UserActivation userActivation = userActivationDao
				.getByActivationKey(activationKey);
		if (userActivation != null) {
			User user = userActivation.getUser();
			user.setEnabled(true);
			userDao.saveOrUpdate(user);
			userActivationDao.delete(userActivation);
		} else {
			throw new UserActivationNotFoundException("User with "
					+ activationKey + " token not found");
		}
	}

	private String formActivationUrl(HttpServletRequest request,
			String randomToken) {
		String url = String.format(
				"%s://%s:%d%s/dist/index.html#activation/%s",
				request.getScheme(), request.getServerName(),
				request.getServerPort(), request.getContextPath(), randomToken);
		return url;
	}

	private String saveUserActivation(User user)
			throws NoSuchAlgorithmException {
		UserActivation userActivation = new UserActivation();
		userActivation.setUser(user);
		String randomMD5Token = getRandomMD5Token();
		userActivation.setToken(randomMD5Token);
		userActivationDao.saveOrUpdate(userActivation);
		return randomMD5Token;
	}

	private String getRandomMD5Token() throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		return token;
	}
}
