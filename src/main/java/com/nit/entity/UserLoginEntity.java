package com.nit.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserLoginEntity {

	private String email;
	private String password;

	public UserLoginEntity() {}

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

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginEntity other = (UserLoginEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "UserLoginEntity [email=" + email + ", password=" + password + "]";
	}	
}
