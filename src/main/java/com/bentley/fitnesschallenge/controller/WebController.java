package com.bentley.fitnesschallenge.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bentley.fitnesschallenge.beans.Family;
import com.bentley.fitnesschallenge.data.DAO;
import com.bentley.fitnesschallenge.forms.LogForm;

@Controller
public class WebController {
	
	@Autowired
	DAO dao;	
	
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String home(Model model, Principal principal, @RequestParam(value = "logForm", required=false) final LogForm logForm) {
		
		Collection<Family> families = dao.getExerciseByFamily().values();
		model.addAttribute("families", families );
		int maxDuration = 0;
		for( Family family : families ) {
			if( maxDuration < family.getTotalDuration() ) {
				maxDuration = family.getTotalDuration();
			}
		}
		
		if( logForm != null && logForm.getDuration() > 0 ) {
			model.addAttribute( "logged", logForm.getDuration() );
		}
		model.addAttribute("maxDuration", maxDuration );
		model.addAttribute("logForm", new LogForm());
		
		return "home";
	}
	
	@PostMapping(value = "/")
	public String logExercise(
	    @ModelAttribute("logForm") LogForm logForm,
	    Model model,
	    Principal principal) {
		dao.logExercise(logForm);
		return home(model, principal, logForm);
	}
}
