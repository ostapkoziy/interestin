package com.interestin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.interestin.model.enums.Sex;

/**
 * Created by dbatuik on 31.01.14.
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_profile_id")
	private Long userProfileId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true)
	@JsonIgnore
	private User user;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	private String phoneNumber;

	@Enumerated(value = EnumType.STRING)
	private Sex sex;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToMany
	@JoinTable(name = "user_hobbies", joinColumns = { @JoinColumn(name = "user_profile_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "hobby_id", nullable = false, updatable = false) })
	private List<Hobby> hobbies;

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "UserProfile{" + "userProfileId=" + userProfileId
				+ ", firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + ", phoneNumber='" + phoneNumber + '\''
				+ ", sex=" + sex + '}';
	}
}
