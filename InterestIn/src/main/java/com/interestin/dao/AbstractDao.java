package com.interestin.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * @author Danylo
 * 
 */
public interface AbstractDao<E, I extends Serializable> {
	E findById(I id);

	void saveOrUpdate(E e);

	void delete(E e);

	List<E> findByCriteria(Criterion criterion);
}
