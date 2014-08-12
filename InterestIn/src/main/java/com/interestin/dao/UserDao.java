package com.interestin.dao;

import com.interestin.model.User;

import java.util.List;

/**
 * @author Danylo
 */
public interface UserDao extends AbstractDao<User, String> {

    List<User> getAllUsers();

    User getUserById(String email);
}
