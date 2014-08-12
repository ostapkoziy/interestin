package com.interestin.dao.impl;

import com.interestin.dao.AbstractDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @param <E>
 * @author Danylo
 */
public class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {
    private static final Logger log = Logger.getLogger(AbstractDaoImpl.class);
    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findById(I id) {
        log.info("Find by id " + id);
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        log.info("Save or Update " + e);
        getCurrentSession().saveOrUpdate(e);
        getCurrentSession().flush();
    }

    @Override
    public void delete(E e) {
        log.info("Delete " + e);
        getCurrentSession().delete(e);
        getCurrentSession().flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria(Criterion criterion) {
        log.info("Find by criteria " + criterion.toString());
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

}
