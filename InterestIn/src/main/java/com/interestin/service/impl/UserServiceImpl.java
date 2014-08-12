package com.interestin.service.impl;

import com.interestin.dao.UserDao;
import com.interestin.model.User;
import com.interestin.model.enums.Authority;
import com.interestin.service.UserActivationService;
import com.interestin.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Danylo
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    UserDao userDao;
    @Autowired
    UserActivationService userActivationService;


    @Override
    public void saveUser(User user, HttpServletRequest httpServletRequest) {
        log.info("Save user with email = " + user.getEmail());
        user.setAuthority(Authority.ROLE_USER);
        user.setEnabled(false);
        user.setLocale(LocaleContextHolder.getLocale().toString());
        userDao.saveOrUpdate(user);
        userActivationService.prepareActivationUser(user, httpServletRequest);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Getting user by email " + email);
        return userDao.getUserById(email);
    }

    @Override
    public void updateUser(User user) {
        log.info("Updating user with email " + user.getEmail());
        userDao.saveOrUpdate(user);
    }


}
