package com.nit.binding;

import java.time.LocalDate;

public class ToDoTask {

	private String taskName;
	private LocalDate taskDate;
	private String timings;
	
	public ToDoTask() {}

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

	@Override
	public String toString() {
		return "ToDoTask [taskName=" + taskName + ", taskDate=" + taskDate + ", timings=" + timings + "]";
	}
}
