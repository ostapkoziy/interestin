package com.interestin.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interestin.model.User;
import com.interestin.model.UserProfile;
import com.interestin.service.UserProfileService;
import com.interestin.service.UserService;

@Controller
@RequestMapping("/user/profile")
public class UserProfilesController {
	private static final String OK_JSON = "{\"status\":\"ok\"}";
	@Autowired
	UserService userService;
	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUserProfile(
			@RequestBody UserProfile userProfile, Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		userProfile.setUser(user);
		userProfileService.saveUserProfile(userProfile);
		return new ResponseEntity<>(OK_JSON, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> getUserProfile(Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<>(user.getUserProfile(), HttpStatus.OK);
	}
}
