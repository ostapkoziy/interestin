package com.interestin.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.interestin.model.Hobby;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hobbies {

	@XmlElement(name = "hobby")
	private List<Hobby> hobbies;

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

}
