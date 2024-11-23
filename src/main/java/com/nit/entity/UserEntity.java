package com.nit.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODOUSER_ASHOK")
public class UserEntity {

	private String username;
	private String gender;
	private Long phoneNumber;
	
	@EmbeddedId
	private UserLoginEntity userLoginEntity;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<ToDoTaskEntity> tasks = new ArrayList<>();
	
	public UserEntity() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserLoginEntity getUserLoginEntity() {
		return userLoginEntity;
	}

	public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
		this.userLoginEntity = userLoginEntity;
	}

	public List<ToDoTaskEntity> getTasks() {
		return tasks;
	}

	public void setTask(ToDoTaskEntity task) {
		tasks.add(task);
	}

	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", userLoginEntity=" + userLoginEntity + "]";
	}	
}
