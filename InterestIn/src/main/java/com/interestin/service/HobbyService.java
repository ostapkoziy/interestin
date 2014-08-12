package com.interestin.service;

import java.util.List;

import com.interestin.model.Hobby;
import com.interestin.model.xml.Hobbies;

public interface HobbyService {

	void saveHobbies(Hobbies hobbies);

	List<Hobby> getParentHobbies();

	List<String> getHobbyCategories(Long hobbyId);

	List<Hobby> getCategoryHobbies(Long hobbyId, String category);
}
