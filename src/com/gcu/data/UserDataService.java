package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.User;

public class UserDataService implements DataAccessInterface<User> {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 


	/**
	 * Method to add a new user using the passed in fields
	 * @param user - User: An object containing a user's information
	 * @return userId - Integer: An integer that is the new user's id
	 */
	@Override
	public int create(User user) {
		
		// Define variable to return
		int userId = 0;
		
		// Checks if the username inputed already exist
		String sqlCheckUsername = "SELECT * FROM USERCREDENTIALS WHERE USERNAME=?";
		
		try {
			SqlRowSet srsUsername = jdbcTemplateObject.queryForRowSet(sqlCheckUsername, user.getUserCredentials().getUsername());


			// If that username doesn't already exist in the database
			if (srsUsername.next() == false) {
				//Creates SQL statements
				String sqlInsertCredentials = "INSERT INTO USERCREDENTIALS (USERNAME, PASSWORD, USER_ID) VALUES(?, ?, ?)";
				String sqlInsertUser = "INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES (NULL, ?, ?, ?, ?)";
				
				try {
					// Insert user information into the users table
					jdbcTemplateObject.update(sqlInsertUser, user.getFirstName(), user.getLastName(), user.getEmail(),user.getPhoneNumber());
					
					// Get the last inserted id of the user and perform query
					String sqlQuery = "SELECT LAST_INSERT_ID() AS LAST_ID FROM USERS";
					
					SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
					srs.next();
					
					// Set the retrieved ID to userId variable
					userId = Integer.parseInt(srs.getString("LAST_ID"));
					
					// Insert user credential information into the usercredentials table
					jdbcTemplateObject.update(sqlInsertCredentials, user.getUserCredentials().getUsername(), user.getUserCredentials().getPassword(), userId);
					
				}
				
				catch(Exception e) {
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
			// Username is already in the database
			else {
				// Set userId to -1 as a failure status
				userId = -1;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return userId;
	}
	
	
	/**
	 * Method to update an existing user
	 * @param user - User: An object containing a user's information
	 * @return status - Integer: An integer representing the success or failure of the update
	 */
	@Override
	public int update(User user) {
		
		// Define variable to return
		int status = 0;
		
		// Creates SQL statements
		String sqlUpdateCredentials = "UPDATE USERCREDENTIALS SET USERNAME=?, PASSWORD=? WHERE ID=?";
		String sqlUpdateUser = "UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE=? WHERE ID=?";
		
		try {
			// Perform update of user credentials, setting status to number of rows affected
			status += jdbcTemplateObject.update(sqlUpdateCredentials, user.getUserCredentials().getUsername(), 
					user.getUserCredentials().getPassword(), user.getId());
			
			// Perform update of user, setting status to number of rows affected
			status += jdbcTemplateObject.update(sqlUpdateUser, user.getFirstName(), user.getLastName(), user.getEmail(), 
					user.getPhoneNumber(), user.getId());	
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return status;
	}

	
	
	
// -------------------- MAY IMPLEMENT LATER --------------------------
	/**
	 * Method to delete a user using the passed in id
	 * @param userId - Integer: An integer of the user's id
	 * @return status - Integer: An integer representing the success or failure of the deletion
	 */
	@Override
	public int delete(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

	/**
	 * Method to view all users
	 * @return users - List<User>: A list of all users
	 */
	@Override
	public List<User> viewAll() {
		
		// Creates a SQL statement
		String sql = "SELECT * FROM USERS INNER JOIN USERCREDENTIALS ON USERS.ID = USERCREDENTIALS.USER_ID;";
		
		// Creates an ArrayList of users that will be filled with all the users from the database
		List<User> users = new ArrayList<User>();
		
		try {
			// Access the database and query all users, and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Create a new user with all attributes and add to users
			while(srs.next()) {
				users.add(new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), 
						srs.getString("EMAIL"), srs.getString("PHONE"), srs.getString("USERNAME"), srs.getString("PASSWORD")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return users;
	}

	
	/**
	 * Method to view all users for REST
	 * @return users - List<User>: A list of all users
	 */
	@Override
	public List<User> viewAllREST() {
		
		// Creates a SQL statement
		String sql = "SELECT * FROM USERS INNER JOIN USERCREDENTIALS ON USERS.ID = USERCREDENTIALS.USER_ID;";
		
		// Creates an ArrayList of users that will be filled with all the users from the database
		List<User> users = new ArrayList<User>();
		
		try {
			// Access the database and query all users, and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Create a new user with all attributes and add to users
			while(srs.next()) {
				users.add(new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), 
						srs.getString("EMAIL"), srs.getString("PHONE"), srs.getString("USERNAME"), srs.getString("PASSWORD")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return users;
	}

	/**
	 * Method to view a user by the userId
	 * @param userId - Integer: An integer of the user's id
	 * @return user - User: An object containing a user's information
	 */
	@Override
	public User viewById(int userId) {
		
		//Creates a SQL statement to be filled in later
		String sql = "SELECT * FROM USERS INNER JOIN USERCREDENTIALS ON USERS.ID = USERCREDENTIALS.USER_ID AND USERS.ID = ?";
		
		//Create a user and set to null
		User currentUser = null;
		
		try {
			// Access the database and query, get back user results
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, userId);
			
			while(srs.next()) {
				currentUser = new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), srs.getString("EMAIL"), srs.getString("PHONE"), srs.getString("USERNAME"), srs.getString("PASSWORD"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return currentUser;
	}

	
	/**
	 * Method that takes in a DataSource from the applicationConfiguration to create a dataSource and JDBC Template Object used to connect and perform CRUD action to the database
	 * @param ds - DataSource: to connect the SQL command to the databases
	 */
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}


	@Override
	public List<User> viewUserREST() {
		// TODO Auto-generated method stub
		return null;
	}

}
