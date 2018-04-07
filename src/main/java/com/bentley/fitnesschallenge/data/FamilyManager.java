package com.bentley.fitnesschallenge.data;

import org.springframework.util.StringUtils;

import com.bentley.fitnesschallenge.beans.Exercise;
import com.bentley.fitnesschallenge.beans.Family;
import com.bentley.fitnesschallenge.beans.User;

public class FamilyManager {
	
	public static int getTotalExerciseMinutes( Family family ) {
		int minutes = 0;
		if( family != null && family.getUsers() != null ) {
			for( User user : family.getUsers() ) {
				if( user != null && user.getExerciseList() != null ) {
					for( Exercise exercise : user.getExerciseList() ) {
						minutes += exercise.getDuration();
					}
				}
			}
		}
		return minutes;
	}
	
	public static void addUser( Family family, User user ) {
		if( family == null ) {
			return;
		}
		
		User existingUser = getUserById( family, user.getUserId() );
		if( existingUser == null ) {
			family.addUser(user);
		} else if( user.getExerciseList() != null ) {
			existingUser.addExcercise( user.getExerciseList() );
			family.updateTotalDuration();
		}
		
		
	}
	
	public static User getUserByName( Family family, String firstName, String lastName ) {
		if( family != null && family.getUsers() != null && !StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName) ) {
			for( User user : family.getUsers() ) {
				if( firstName.equals(user.getFirstName() ) && lastName.equals(user.getLastName() ) ) {
					return user;
				}
			}
		}
		return null;
	}
	
	public static User getUserById( Family family, int userId ) {
		if( family != null && family.getUsers() != null ) {
			for( User user : family.getUsers() ) {
				if( userId == user.getUserId() ) {
					return user;
				}
			}
		}
		return null;
	}

}
