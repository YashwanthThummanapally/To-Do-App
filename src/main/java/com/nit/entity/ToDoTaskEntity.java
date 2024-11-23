package com.nit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODOTASK_ASHOK")
public class ToDoTaskEntity {

	@Id
	@GeneratedValue(generator = "taskId")
	@GenericGenerator(name = "taskId", strategy = "com.nit.generator.TaskIdGenerator")
	private String taskId;
	
	private String taskName;
	private LocalDate taskDate;
	private String timings;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	@JoinColumn(name = "PASSWORD")
	private UserEntity user;

	public ToDoTaskEntity() {}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ToDoTaskEntity [taskId=" + taskId + ", taskName=" + taskName + ", taskDate=" + taskDate + ", timings="
				+ timings + ", user=" + user + "]";
	}	
}
