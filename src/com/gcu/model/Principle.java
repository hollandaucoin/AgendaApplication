package com.gcu.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Principle {
	
	//The current user
	private User currentUser;
	
	
	/**
	 * Default Constructor
	 */
	public Principle() {
		currentUser = new User();
	}

	/**
	 * Getters and setters
	 */
	public User getCurrentUser()  {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
