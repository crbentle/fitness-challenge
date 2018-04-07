package com.bentley.fitnesschallenge.beans;

import java.util.ArrayList;
import java.util.List;

public class Family {
	
	private List<User> users;
	private String lastName;
	private int totalDuration = 0;
	
	public Family( String lastName ) {
		this.lastName = lastName;
	}
	
	public void updateTotalDuration() {
		totalDuration = 0;
		for( User user : users ) {
			totalDuration += user.getTotalDuration();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(30 / 60);
		System.out.println(30 % 60);
		System.out.println(210 / 60);
		System.out.println(210 % 60);
		Family fam = new Family("test");
		fam.setTotalDuration(121);
		System.out.println(fam.getDurationDisplay());
	}
	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
		totalDuration = 0;
		for( User user : users ) {
			totalDuration += user.getTotalDuration();
		}
	}
	
	public void addUser( User user ) {
		if( users == null ) {
			users = new ArrayList<User>();
		}
		users.add(user);
		totalDuration += user.getTotalDuration();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	@Override
	public String toString() {
		return "Family [users=" + users + ", lastName=" + lastName + ", totalDuration=" + totalDuration + "]";
	}

}
