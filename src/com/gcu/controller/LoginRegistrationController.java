package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Principle;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

@Controller
@RequestMapping("/user")
public class LoginRegistrationController {
	
	private UserBusinessInterface service;
	
	@Autowired
	public void setUserService(UserBusinessInterface service) {
		this.service = service;
	}
	
	@Autowired
	Principle session; 
	
	
	/**
	 * Method to show the loginPage.jsp form, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView displayLoginForm() {
		
		return new ModelAndView("loginPage", "userCredential", new UserCredentials());
	}
	
	
	/**
	 * Method to verify is the userCredentials provided are valid
	 * @param userCredentials - UserCredentials
	 * @param result - BindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("userCredential") UserCredentials userCredential, BindingResult result) {
		
		// Validate the form submitted, if it has errors send back to loginPage
		if(result.hasErrors() == true) {
			return new ModelAndView("loginPage", "userCredential", userCredential);
		}
		// The form doesn't have errors
		else {
			// Set userId to result of authenticateUser method (returns ID of user if it exists)
			int userId = service.authenticateUser(userCredential);
			
			// Verify if the user ID exists
			if(userId > 0) {
				// Set user as session variable
				session.setCurrentUser(service.getUserById(userId));
				// Send to homePage
				return new ModelAndView("homePage", "userCredential", userCredential);
			}
			// The input was incorrect
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing an invalid credentials message and send back to the loginPage
				mv.addObject("message", "Invalid login credentials, please try again.<br><br>");
				mv.setViewName("loginPage");
				mv.addObject("userCredential", userCredential);

				return mv;
			}
		}
	}
	
	/**
	 * Method to show the regsitrationPage.jsp form, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/registration", method = RequestMethod.GET)
	public ModelAndView displayRegistrationForm() {
		
		return new ModelAndView("registrationPage", "user", new User());
	}

	/**
	 * Method to register a new user
	 * @param user - User
	 * @param result - BindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		// Validate the form, if it has errors send back to registrationPage
		if(result.hasErrors() == true) {
			
			return new ModelAndView("registrationPage", "user", user);
		}
		// The form doesn't have errors
		else {
			
			// Set userId to result of addUser method (returns ID of user if it exists)
			int userId = service.addUser(user);
			
			// If the user was added, send to homePage
			if(userId > 0) {
				
				// Set user as session variable
				session.setCurrentUser(service.getUserById(userId));
				
				return new ModelAndView("homePage", "user", user);
			}
			// Username already exists
			else if(service.addUser(user) == -1) {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing an invalid user message and send back to the loginPage
				mv.addObject("message", "Username already exists, please try again.<br><br>");
				mv.setViewName("registrationPage");
				mv.addObject("user", user);

				return mv;
			}
			// An error occurred
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing an invalid user message and send back to the loginPage
				mv.addObject("message", "Registration failed, please try again.<br><br>");
				mv.setViewName("registrationPage");
				mv.addObject("user", user);

				return mv;
			}
		}
	}
	
}
