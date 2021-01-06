package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCredentials {
	
	
	// Variable to store the user's username, used for login. Required and has size limit
	@NotNull(message="Please enter a Username. This is a required field.")
	@Size(min=5, max=30, message="Username must be between 5 and 30 characters")
	private String username;
	
	// Variable to store the user's password, used for login. Required and has size limit
	@NotNull(message="Please enter a Password. This is a required field..")
	@Size(min=5, max=30, message="Password must be between 5 and 30 characters")
	private String password;
	
	
	/**
	 * Constructor for a user's credentials
	 */
	public UserCredentials() {
		username = "";
		password = "";
	}
	
	/**
	 * Constructor for a user's credentials
	 * @param username - String: The username a user has created to use to login
	 * @param password - String: The password a user has created to use to login
	 */
	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	
	/**
	 * Getters and Setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
