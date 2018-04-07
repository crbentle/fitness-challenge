package com.bentley.fitnesschallenge.beans;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	boolean active;
	List<Exercise> exerciseList;
	int totalDuration = 0;
	
	public String getDurationDisplay() {
		int hours = totalDuration / 60;
		int minutes = totalDuration % 60;
		String display = "";
		if( hours > 0 ) {
			display = hours + " hrs";
			if( minutes > 0 ) {
				display += ", " + minutes + " mins";
			}
		} else {
			display = minutes + " mins";
		}
		return display;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Exercise> getExerciseList() {
		return exerciseList;
	}
	public void setExerciseList(List<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
		totalDuration = 0;
		for( Exercise e : exerciseList ) {
			totalDuration += e.getDuration();
		}
	}
	public void addExcercise( List<Exercise> exercise ) {
		if( exercise == null ) {
			return;
		}
		if( exerciseList == null ) {
			exerciseList = new ArrayList<Exercise>();
		}
		exerciseList.addAll(exercise);
		for( Exercise e : exercise ) {
			totalDuration += e.getDuration();
		}
	}

	public void addExcercise( Exercise exercise ) {
		if( exercise == null ) {
			return;
		}
		if( exerciseList == null ) {
			exerciseList = new ArrayList<Exercise>();
		}
		exerciseList.add(exercise);
		totalDuration += exercise.getDuration();
	}
	
	public int getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", active=" + active
				+ ", exerciseList=" + exerciseList + ", totalDuration=" + totalDuration + "]";
	}

}
