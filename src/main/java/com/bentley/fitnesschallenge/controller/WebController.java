package com.bentley.fitnesschallenge.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.bentley.fitnesschallenge.beans.Family;
import com.bentley.fitnesschallenge.beans.User;
import com.bentley.fitnesschallenge.data.DAO;
import com.bentley.fitnesschallenge.data.UserService;
import com.bentley.fitnesschallenge.data.dto.UserDTO;
import com.bentley.fitnesschallenge.exception.EmailExistsException;
import com.bentley.fitnesschallenge.forms.LogForm;

@Controller
public class WebController {
	
	@Autowired
	DAO dao;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String home(Model model, Principal principal) {
		
		Collection<Family> families = dao.getExerciseByFamily().values();
		model.addAttribute("families", families );
		int maxDuration = 0;
		for( Family family : families ) {
			if( maxDuration < family.getTotalDuration() ) {
				maxDuration = family.getTotalDuration();
			}
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
		return home(model, principal);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(WebRequest request, Model model) {
		UserDTO userDto = new UserDTO();
	    model.addAttribute("user", userDto);
	    model.addAttribute("type", "login");
	    return "login";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDTO userDto = new UserDTO();
	    model.addAttribute("user", userDto);
	    model.addAttribute("type", "register");
	    return "login";
	}
	 
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUserAccount
	      (@ModelAttribute("user") @Valid UserDTO accountDto, 
	      BindingResult result, Model model) {    
	    User registered = null;//new User();
	    if (!result.hasErrors()) {
	        registered = createUserAccount(accountDto, result);
	    }
	    if (registered == null) {
	    	result.rejectValue("userName", "Already exists");
	    	model.addAttribute("userNameError", "Username alread exists");
	    }
	    
	    
	    if (result.hasErrors()) {
		    model.addAttribute("user", accountDto);
		    model.addAttribute("type", "register");
	        return "login";
	    } 
	    else {
	    	userService.authenticate(accountDto.getUserName(), accountDto.getPassword());
	        return "redirect:/";
	    }
	}
	private User createUserAccount(UserDTO accountDto, BindingResult result) {
	    User registered = null;
	    try {
	        registered = userService.registerNewUserAccount(accountDto);
	    } catch (EmailExistsException e) {
	        return null;
	    }    
	    return registered;
	}
}
