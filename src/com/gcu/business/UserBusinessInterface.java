package com.gcu.business;

import java.util.List;

import com.gcu.model.User;
import com.gcu.model.UserCredentials;

public interface UserBusinessInterface {

	/**
	 * See UserBusinessService.authenticateUser
	 */
	public int authenticateUser(UserCredentials userCredentials);
	
	/**
	 * See UserBusinessService.addUser
	 */
	public int addUser(User user);
	
	/**
	 * See UserBusinessService.getUserById
	 */
	public User getUserById(int userId);
	
	/**
	 * See UserBusinessService.getAllUsersREST
	 */
	public List<User> getAllUsersREST();
	
}
