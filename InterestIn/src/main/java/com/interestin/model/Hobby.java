package com.interestin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "hobbies")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hobby {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "hobby_id")
	@XmlTransient
	private Long hobbyId;
	@XmlAttribute
	@Column(unique = true, nullable = false)
	private String key;
	@XmlAttribute
	private Boolean disabled;
	@XmlAttribute(required = false)
	private String category;
	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@JsonIgnore
	private Hobby parent;
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@XmlElement(name = "hobby")
	@JsonIgnore
	private List<Hobby> hobbies;

	@ManyToMany(mappedBy = "hobbies", fetch = FetchType.LAZY)
	@XmlTransient
	@JsonIgnore
	private List<UserProfile> userProfiles;

	public Long getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(Long hobbyId) {
		this.hobbyId = hobbyId;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Hobby getParent() {
		return parent;
	}

	public void setParent(Hobby parent) {
		this.parent = parent;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		Hobby other = (Hobby) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hobby [hobbyId=" + hobbyId + ", key=" + key + ", disabled="
				+ disabled + ", category=" + category + "]";
	}

}
