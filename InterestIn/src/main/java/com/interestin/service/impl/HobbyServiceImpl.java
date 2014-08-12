package com.interestin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interestin.dao.HobbyDao;
import com.interestin.model.Hobby;
import com.interestin.model.xml.Hobbies;
import com.interestin.service.HobbyService;

@Service
public class HobbyServiceImpl implements HobbyService {

	@Autowired
	HobbyDao hobbyDao;

	@Override
	public void saveHobbies(Hobbies hobbies) {
		for (Hobby hobby : hobbies.getHobbies()) {
			saveHobby(hobby);
		}
	}

	private void saveHobby(Hobby hobby) {
		Hobby dbHobby = hobbyDao.getByKey(hobby.getKey());
		if (dbHobby != null) {
			dbHobby.setCategory(hobby.getCategory());
			dbHobby.setDisabled(hobby.getDisabled());
			dbHobby.setHobbies(hobby.getHobbies());
			hobby = dbHobby;
		}
		hobbyDao.saveOrUpdate(hobby);
		if (hobby.getHobbies() != null && !hobby.getHobbies().isEmpty()) {
			for (Hobby hobbyChildren : hobby.getHobbies()) {
				hobbyChildren.setParent(hobby);
				saveHobby(hobbyChildren);
			}
		}
	}

	@Override
	public List<Hobby> getParentHobbies() {
		return hobbyDao.getParentHobbies();
	}

	@Override
	public List<String> getHobbyCategories(Long hobbyId) {
		return hobbyDao.getHobbyCategories(hobbyId);
	}

	@Override
	public List<Hobby> getCategoryHobbies(Long hobbyId, String category) {
		return hobbyDao.getCategoryHobbies(hobbyId, category);
	}

}
