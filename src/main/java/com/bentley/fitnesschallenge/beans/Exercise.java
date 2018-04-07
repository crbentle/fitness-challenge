package com.bentley.fitnesschallenge.beans;

import java.util.Date;

public class Exercise {
	
	private int id;
	private int duration;
	private String description;
	private int userId;
	private Date exerciseDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getExerciseDate() {
		return exerciseDate;
	}
	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", duration=" + duration + ", description=" + description + ", userId=" + userId
				+ ", exerciseDate=" + exerciseDate + "]";
	}

}
