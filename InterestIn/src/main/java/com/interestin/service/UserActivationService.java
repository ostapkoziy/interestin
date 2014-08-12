package com.interestin.service;

import com.interestin.exception.UserActivationNotFoundException;
import com.interestin.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Danylo on 30.01.14.
 */
public interface UserActivationService {
    void prepareActivationUser(User user, HttpServletRequest httpServletRequest);

    void activateUser(String activationKey) throws UserActivationNotFoundException;
}
