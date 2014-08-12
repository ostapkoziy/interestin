package com.interestin.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by dbatuik on 29.01.14.
 */
@Entity
@Table(name = "user_activations")
public class UserActivation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_activation_id")
	private Long userActivationId;
	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	private String token;

	public Long getUserActivationId() {
		return userActivationId;
	}

	public void setUserActivationId(Long userActivationId) {
		this.userActivationId = userActivationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserActivation{" + "userActivationId=" + userActivationId
				+ ", user=" + user + ", token='" + token + '\'' + '}';
	}
}
