package com.bentley.fitnesschallenge.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.bentley.fitnesschallenge.beans.Exercise;
import com.bentley.fitnesschallenge.beans.Family;
import com.bentley.fitnesschallenge.beans.User;
import com.bentley.fitnesschallenge.forms.LogForm;

@Component
public class DAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Map<String, Family> getExerciseByFamily() {
		String sql = "Select * from users u join exercise e on u.username = e.username";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Family>>() {
			Map<String, Family> familyMap = new HashMap<String, Family>();
			@Override
			public Map<String, Family> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while( rs.next() ) {
					String lastName = rs.getString("LASTNAME");
					Family family = familyMap.get(lastName);
					if( family == null ) {
						family = new Family( lastName );
					}
					
					User user = new User();
					user.setUserId(rs.getInt("USERID"));
					user.setFirstName(rs.getString("FIRSTNAME"));
					user.setUserName(rs.getString("USERNAME"));
					user.setLastName(lastName);
					
					Exercise exercise = new Exercise();
					exercise.setDescription(rs.getString("DESCRIPTION"));
					exercise.setDuration(rs.getInt("DURATION"));
					exercise.setExerciseDate(rs.getDate("EXERCISE_DATE"));
					
					user.addExcercise(exercise);
					
					FamilyManager.addUser(family, user);
					familyMap.put(lastName, family);
					
				}
				return familyMap;
			}
			
		});
	}
	
	public void logExercise( LogForm form ) {
		String sql = "INSERT INTO EXERCISE (USERNAME, DURATION, DESCRIPTION, EXERCISE_DATE) VALUES (?,?,?,?)";
		jdbcTemplate.update( sql, form.getUserName(), form.getDuration(), form.getDescription(), form.getExerciseDate() );
	}

}
