package com.bentley.fitnesschallenge.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bentley.fitnesschallenge.beans.User;

@Component
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public User findByUserName(String userName) {
		//[{USERID=1, FIRSTNAME=Chris, LASTNAME=Bentley, PASSWORD=null, ACTIVE=1, USERNAME=crbentle}]
//		System.out.println(jdbcTemplate.queryForList("select * from users where username = ?", new Object[] {userName}));
		List<User> users = jdbcTemplate.query("select * from users where username = ?", new Object[] { userName },
				new UserRowMapper());
		System.out.println(users);
		if( !users.isEmpty() ) {
			return users.get(0);
		}
		return null;
//		return new User();
	}
	
	public void createNewUser( User user ) {
		String sql = "INSERT INTO USERS (FIRSTNAME, LASTNAME, PASSWORD, USERNAME, ACTIVE) VALUES (?,?,?,?,?)";
		jdbcTemplate.update( sql, new Object[]{ user.getFirstName(), user.getLastName(), user.getPassword(), user.getUserName(), true});
	}
	
	public User loadUserByUsername( String userName ) {
		return findByUserName(userName);
	}

}



class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("USERID"));
		user.setFirstName(rs.getString("FIRSTNAME"));
		user.setLastName(rs.getString("LASTNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setActive(rs.getBoolean("ACTIVE"));
		user.setUserName(rs.getString("USERNAME"));
		return user;
	}
	
}
