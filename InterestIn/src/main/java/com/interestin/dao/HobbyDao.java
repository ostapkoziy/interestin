package com.interestin.dao;

import java.util.List;

import com.interestin.model.Hobby;

public interface HobbyDao extends AbstractDao<Hobby, String> {

	List<Hobby> getParentHobbies();

	Hobby getByKey(String key);

	List<String> getHobbyCategories(Long hobbyId);

	List<Hobby> getCategoryHobbies(Long hobbyId, String category);

}
