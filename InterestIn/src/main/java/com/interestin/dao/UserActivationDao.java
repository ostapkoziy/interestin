package com.interestin.dao;

import com.interestin.model.UserActivation;

/**
 * Created by dbatuik on 29.01.14.
 */
public interface UserActivationDao extends AbstractDao<UserActivation, String> {
    UserActivation getByActivationKey(String activationKey);
}
