package com.interestin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interestin.model.Hobby;
import com.interestin.service.HobbyService;

@Controller
@RequestMapping("/hobbies")
public class HobbyController {

	@Autowired
	HobbyService hobbyService;

	@RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hobby>> getParentHobbies() {
		List<Hobby> hobbies = hobbyService.getParentHobbies();
		return new ResponseEntity<>(hobbies, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{hobbyId}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getHobbyCategories(
			@PathVariable Long hobbyId) {
		List<String> categories = hobbyService.getHobbyCategories(hobbyId);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{hobbyId}/categories/{category}/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hobby>> getHobbyHobbies(
			@PathVariable Long hobbyId, @PathVariable String category) {
		List<Hobby> hobbies = hobbyService.getCategoryHobbies(hobbyId, category);
		return new ResponseEntity<>(hobbies, HttpStatus.OK);
	}
}
