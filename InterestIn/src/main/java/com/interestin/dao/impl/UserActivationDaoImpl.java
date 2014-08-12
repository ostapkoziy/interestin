package com.interestin.dao.impl;

import com.interestin.dao.UserActivationDao;
import com.interestin.model.UserActivation;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbatuik on 29.01.14.
 */
@Repository
public class UserActivationDaoImpl extends AbstractDaoImpl<UserActivation, String> implements UserActivationDao {
    protected UserActivationDaoImpl() {
        super(UserActivation.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public UserActivation getByActivationKey(String activationKey) {
        String hql = "SELECT ua FROM UserActivation ua WHERE ua.token=:token";
        Query query = getCurrentSession().createQuery(hql);
        query.setString("token", activationKey);
        List<UserActivation> userActivations = query.list();
        if (!userActivations.isEmpty()) {
            return userActivations.get(0);
        }
        return null;
    }
}
