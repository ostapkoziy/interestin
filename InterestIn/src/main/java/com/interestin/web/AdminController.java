package com.interestin.web;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.interestin.model.xml.Countries;
import com.interestin.model.xml.Hobbies;
import com.interestin.service.CountryService;
import com.interestin.service.HobbyService;

/**
 * Created by dbatuik on 21.06.2014.
 */
@Controller
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class);
	@Autowired
	CountryService countryService;
	@Autowired
	HobbyService hobbyService;

	@RequestMapping("/adm")
	public String admin() {
		log.info("admin page");
		return "admin";
	}

	@RequestMapping(value = "/countries", method = RequestMethod.POST)
	public String countries(@RequestParam MultipartFile file)
			throws JAXBException, IOException {
		log.info("Countries file uploaded");
		JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Countries countries = (Countries) unmarshaller.unmarshal(file
				.getInputStream());
		countryService.saveCountries(countries);
		return admin();
	}

	@RequestMapping(value = "/hobbies", method = RequestMethod.POST)
	public String hobbies(@RequestParam MultipartFile file)
			throws JAXBException, IOException {
		log.info("Hobbies file uploaded");
		JAXBContext jaxbContext = JAXBContext.newInstance(Hobbies.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Hobbies hobbies = (Hobbies) unmarshaller.unmarshal(file
				.getInputStream());
		hobbyService.saveHobbies(hobbies);
		return admin();
	}
}
