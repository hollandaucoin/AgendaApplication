package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	private int id;
	
	// Variable to store the user's first name. Required and has size limit
	@NotNull(message="First name is a required field.")
	@Size(min=2, max=30, message="First name must be between 2 and 30 characters")
	private String firstName;
	
	// Variable to store the user's last name. Required and has size limit
	@NotNull(message="Last name is a required field.")
	@Size(min=2, max=30, message="Last name must be between 2 and 30 characters")
	private String lastName;
	
	// Variable to store the user's email address. Required and has a pattern to meet
	@NotNull(message="Email is a required field.")
	@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="Please enter a valid email address.")
	private String email;
	
	// Variable to store the user's phone number. Required and has a pattern to meet
	@NotNull(message="Phone number is a required field.")
	@Pattern(regexp="([0-9]{3}+-[0-9]{3}+-[0-9]{4})|([0-9]{10})", message="Please enter a valid phone number.")
	private String phoneNumber;

	// Create an object of UserCredentials
	UserCredentials userCredentials;
	
	
	/**
	 * Empty constructor for the user
	 */
	public User() {
		this.id = 0;;
		this.firstName = "";
		this.lastName = "";
		this.phoneNumber = "";
		this.email = "";
		
		this.userCredentials = new UserCredentials();
	}
	
	/**
	 * Constructor for the user
	 * @param id - Integer: The user's id
	 * @param firstName - String: The user's first name
	 * @param lastName - String: The user's last name
	 * @param username - String: The user's username used to login
	 * @param password - String: The user's password used to login
	 * @param phoneNumber - String: The user's phoneNumber
	 * @param email - String: The user's email address
	 */
	public User(int id, String firstName, String lastName, String phoneNumber, String email, String username, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		
		UserCredentials userCred = new UserCredentials(username, password);
		this.setUserCredentials(userCred);
	}

	
	/**
	 * Getters and setters
	 */
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id; 
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}


}
