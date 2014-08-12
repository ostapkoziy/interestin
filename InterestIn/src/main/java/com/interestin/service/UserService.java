package com.interestin.service;

import com.interestin.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Danylo
 */
public interface UserService {

    void saveUser(User user, HttpServletRequest httpServletRequest);

    List<User> getAllUsers();

    User getUserByEmail(String name);

    void updateUser(User user);
}
