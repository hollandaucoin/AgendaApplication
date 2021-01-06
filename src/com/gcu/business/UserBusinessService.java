package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

public class UserBusinessService implements UserBusinessInterface {
	
	DataAccessInterface<User> dao;
	
	@Autowired
	public void setUserService(DataAccessInterface<User> dao) {
		this.dao = dao;
	}
	

	/**
	 * Method to authenticate the passed in user credentials by looping through all users
	 * @param userCredentials - UserCredentials: An object containing a user's inputted username and password
	 * @return userId - Integer: An integer representing the userId found matching the user credentials
	 */
	@Override
	public int authenticateUser(UserCredentials userCredentials) {
		
		// Initialize variable to set the user valid or not
		int userId = 0;
		
		// Gets the full list of users
		List<User> users = dao.viewAll();
		
		// For loop to iterate through the list of users to match the credentials
		for(int i = 0; i < users.size(); i ++) {
			if(users.get(i).getUserCredentials().getUsername().equals(userCredentials.getUsername()) && users.get(i).getUserCredentials().getPassword().equals(userCredentials.getPassword())) {
				userId = users.get(i).getId();
				break;
			}
		}
		// Return the matching userId (may be 0 if no match)
		return userId;
	}

	/**
	 * Method to add a new user's information by calling the UserDataInterface.createUser method
	 * @param user - User: An object containing a user's inputed information
	 * @return Integer: An integer representing the status of a user being added
	 */
	@Override
	public int addUser(User user) {

		return dao.create(user);
	}

	/**
	 * Method to get a user by using the ID (used to set session)
	 * @param userId - Integer: An integer that represent the current user's id
	 * @return User: A user object of the current user
	 */
	@Override
	public User getUserById(int userId) {
		
		return dao.viewById(userId);
	}


	/**
	 * Method to get all users by calling the UserDataInterface.viewAllREST method
	 * @return allUsers - List<User>: A list of user objects for all events
	 */
	@Override
	public List<User> getAllUsersREST() {
		
		// Gets the full list of users
		List<User> allUsers = dao.viewAllREST();
		
		return allUsers;
	}

}
