package com.bentley.fitnesschallenge.forms;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LogForm {

	private String userName;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date exerciseDate;
	private int duration;
	private String description;
	
	public LogForm() {}
	public LogForm( String userName ) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getExerciseDate() {
		return exerciseDate;
	}
	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LogForm [userName=" + userName + ", exerciseDate=" + exerciseDate + ", duration=" + duration
				+ ", description=" + description + "]";
	}
	
}
