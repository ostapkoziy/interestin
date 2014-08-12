package com.interestin.model;

import java.util.Date;

import com.interestin.model.enums.Authority;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Danylo
 */
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private Long userId;

	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	@Enumerated(value = EnumType.STRING)
	private Authority authority;
	private Boolean enabled;
	private String locale;
	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	UserProfile userProfile;
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	UserActivation userActivation;

	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserActivation getUserActivation() {
		return userActivation;
	}

	public void setUserActivation(UserActivation userActivation) {
		this.userActivation = userActivation;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password="
				+ password + ", authority=" + authority + ", enabled="
				+ enabled + ", locale=" + locale + ", creationDate="
				+ creationDate + "]";
	}

}
