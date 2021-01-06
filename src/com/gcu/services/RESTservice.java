package com.gcu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.EventBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Event;
import com.gcu.model.User;

@RestController()
@RequestMapping("/service")
public class RESTservice {

	@Autowired
	EventBusinessInterface eventService;
	
	@GetMapping("/events")
	public List<Event> getEvents() {
		
		List<Event> events = eventService.getAllEventsREST();
		
		return events;
	}
	
	
	@GetMapping("/userEvents")
	public List<Event> getUserEvents() {
		
		List<Event> events = eventService.getUserEventsREST();
		
		return events;
	}
	
	
	@Autowired
	UserBusinessInterface userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		
		List<User> users = userService.getAllUsersREST();
		
		return users;
	}
	
}
