package com.interestin.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.interestin.model.Country;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Countries {

	@XmlElement(name = "country")
	private List<Country> countries;

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}
