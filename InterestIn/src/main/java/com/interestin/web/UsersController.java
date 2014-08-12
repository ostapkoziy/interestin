package com.interestin.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interestin.exception.UserActivationNotFoundException;
import com.interestin.model.User;
import com.interestin.service.UserActivationService;
import com.interestin.service.UserService;

/**
 * @author Danylo
 */
@Controller
@RequestMapping("/users")
public class UsersController {
	private static final Logger log = Logger.getLogger(UsersController.class);
	private static final String OK_JSON = "{\"status\":\"ok\"}";
	@Autowired
	UserService userService;
	@Autowired
	UserActivationService userActivationService;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody User user,
			HttpServletRequest httpServletRequest) {
		log.info("/users RequestMethod.POST");
		userService.saveUser(user, httpServletRequest);
		return new ResponseEntity<String>(OK_JSON, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(Principal principal) {
		log.info("/users RequestMethod.GET");
		User user = principal != null ? userService
				.getUserByEmail(principal.getName()) : null;
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> activate(@RequestBody String activationKey)
			throws UserActivationNotFoundException {
		log.info("/users/activate RequestMethod.POST");
		userActivationService.activateUser(activationKey);
		return new ResponseEntity<String>(OK_JSON, HttpStatus.OK);
	}
}
