package com.interestin.web;

import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.interestin.model.User;
import com.interestin.service.UserService;
import com.interestin.utils.InterestInResourceBundleMessageSource;

/**
 * Created by Danylo on 15.02.14.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {
	private static final Logger log = Logger
			.getLogger(MessagesController.class);
	private static final String OK_JSON = "{\"status\":\"ok\"}";
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> getMessages(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("/messages RequestMethod.GET");
		InterestInResourceBundleMessageSource messageSource = new InterestInResourceBundleMessageSource();
		Map<String, String> messages = messageSource
				.getAllMessages(chooseLocale(locale, principal, request,
						response));
		return new ResponseEntity<Map<String, String>>(messages, HttpStatus.OK);
	}

	private Locale chooseLocale(Locale locale, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		if (locale != null) {
			return locale;
		} else if (principal != null) {
			User user = userService.getUserByEmail(principal.getName());
			setCookieLocale(user.getLocale(), request, response);
			return StringUtils.parseLocaleString(user.getLocale());
		} else {
			return LocaleContextHolder.getLocale();
		}
	}

	private void setCookieLocale(String locale, HttpServletRequest request,
			HttpServletResponse response) {
		LocaleResolver localeResolver = RequestContextUtils
				.getLocaleResolver(request);
		if (localeResolver == null) {
			throw new IllegalStateException(
					"No LocaleResolver found: not in a DispatcherServlet request?");
		}
		localeResolver.setLocale(request, response,
				StringUtils.parseLocaleString(locale));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/locale", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeLocale(@RequestBody String locale,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
		log.info("/messages/locale RequestMethod.POST");
		setCookieLocale(locale, request, response);
		if (principal != null) {
			User user = userService.getUserByEmail(principal.getName());
			if (user != null) {
				user.setLocale(locale);
				userService.updateUser(user);
			}
		}
		return new ResponseEntity<String>(OK_JSON, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/locale", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Locale> getLocale(Locale locale, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("/messages/locale RequestMethod.GET");
		return new ResponseEntity<Locale>(chooseLocale(locale, principal,
				request, response), HttpStatus.OK);
	}
}
