package com.interestin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by dbatuik on 31.01.14.
 */
@Entity
@Table(name = "countries")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "country_id")
	@XmlTransient
	private Long countryId;
	@XmlAttribute
	@Column(unique = true, nullable = false)
	private String key;
	@XmlAttribute
	private Boolean disabled;
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	@XmlElement(name = "city")
	@JsonIgnore
	private List<City> cities;
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	@XmlTransient
	@JsonIgnore
	private List<UserProfile> userProfiles;

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", key=" + key
				+ ", disabled=" + disabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
