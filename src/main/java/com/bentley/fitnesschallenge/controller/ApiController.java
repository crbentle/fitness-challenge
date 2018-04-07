package com.bentley.fitnesschallenge.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bentley.fitnesschallenge.beans.Family;
import com.bentley.fitnesschallenge.data.DAO;

@RestController
public class ApiController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DAO dao;
	
	@RequestMapping("/getExerciseTotal")
	public int getExerciseTotal( @RequestParam(value = "userid") String userId ) {
		Integer duration = jdbcTemplate.queryForObject("select sum(duration) from exercise where userid = ?", new Object[] { userId }, Integer.class );
		int sum = duration == null ? 0 : duration;
		return sum;
	}
	
	@RequestMapping("/getUsers")
	public List<Map<String, Object>> getUsers() {
		//return jdbcTemplate.queryForList("select * from users u join exercise e on u.userid = e.userid");
		return jdbcTemplate.queryForList("select firstname, lastname, sum(duration) total from users u join exercise e on u.userid = e.userid group by lastname, firstname order by lastname");
	}
	
	@RequestMapping("/getFamilies")
	public Map<String, Family> getFamilies() {
		return dao.getExerciseByFamily();
	}

}
