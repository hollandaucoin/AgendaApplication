package com.gcu.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.Event;
import com.gcu.model.Principle;

public class EventDataService implements DataAccessInterface<Event> {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	@Autowired
	Principle session; 


	/**
	 * Method to add a new event using the passed in fields
	 * @param event - Event: An object containing an event's information
	 * @return status - Integer: An integer representing the success or failure of the creation
	 */
	@Override
	public int create(Event event) {
		
		// Define status variable to return
		int status = 0;
		
		// Check if the start date entered is before now
		if(event.getStart().isBefore(LocalDateTime.now())) {
			status = -1;
		}
		// Check if the end date entered is before now
		else if (event.getEnd().isBefore(LocalDateTime.now())) {
			status = -2;
		}
		// Check if the end date entered is the start date entered
		else if (event.getEnd().isBefore(event.getStart())) {
			status = -3;
		}
		// The start date and time and the end date and time are valid, it can be inserted
		else {
			
			// Insert statement using the variables in the given order
			String sql = "INSERT INTO EVENTS (ID, USER_ID, TEXT, START_DATE_TIME, END_DATE_TIME, LOCATION, DESCRIPTION) VALUES (NULL, ?, ?, '" + event.getStart() + "', '" + event.getEnd() + "', ?, ?)";
			
			try {
				status = jdbcTemplateObject.update(sql, session.getCurrentUser().getId(), event.getText(), event.getLocation(), event.getDescription());
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new DatabaseException(e);
			}
		}
		return status; 
	}
	

	/**
	 * Method to update an existing event
	 * @param event - Event: An object containing an event's information
	 * @return status - Integer: An integer representing the success or failure of the update
	 */
	@Override
	public int update(Event event) {
		
		// Define variable to return
		int status = 0;
		
		// Check if the start date entered is before now
		if(event.getStart().isBefore(LocalDateTime.now())) {
			status = -1;
		}
		// Check if the end date entered is before now
		else if (event.getEnd().isBefore(LocalDateTime.now())) {
			status = -2;
		}
		// Check if the end date entered is the start date entered
		else if (event.getEnd().isBefore(event.getStart())) {
			status = -3;
		}
		// The start date and time and the end date and time are valid, it can be updated
		else {
			// Update statement using the variables in the given order
			String sqlUpdate = "UPDATE EVENTS SET TEXT=?, START_DATE_TIME='" + event.getStart() + "', END_DATE_TIME='" + event.getEnd() + "', LOCATION=?, DESCRIPTION=? WHERE ID=?";

			try {
				// Perform update of event, setting status to number of rows affected
				status = jdbcTemplateObject.update(sqlUpdate, event.getText(), event.getLocation(), event.getDescription(), event.getId());
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new DatabaseException(e);
			}
		}
		return status;
	}
	

	/**
	 * Method to delete an event using the passed in id
	 * @param eventId - Integer: An integer of the event's id
	 * @return status - Integer: An integer representing the success or failure of the deletion
	 */
	@Override
	public int delete(int eventId) {
		
		// Define variable to return
		int status = 0;
		
		// Delete statement using eventId variable
		String sqlDelete = "DELETE FROM EVENTS WHERE ID = ?";
		
		try {
			//Query the Database
			status = jdbcTemplateObject.update(sqlDelete, eventId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return status;
	}

	
	/**
	 * Method to view all events of the current user
	 * @return events - List<Event>: A list containing event objects for all events
	 */
	@Override
	public List<Event> viewAll() {
		
		// Creates SQL statement
		String sqlQuery = "SELECT * FROM EVENTS WHERE USER_ID=? AND END_DATE_TIME >= '" + LocalDateTime.now() + "' ORDER BY START_DATE_TIME";
		
		// Creates an ArrayList of events that will be filled with all the events from the database
		List<Event> events = new ArrayList<Event>();
		
		try {
			// Accesses the database and query all events, and is given a results set with information of all events
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, session.getCurrentUser().getId());
			
			// Create a new event with all attributes and add to events
			while(srs.next()) {
				events.add(new Event(srs.getInt("ID"), srs.getInt("USER_ID"), srs.getString("TEXT"), srs.getTimestamp("START_DATE_TIME").toLocalDateTime(), 
						srs.getTimestamp("END_DATE_TIME").toLocalDateTime(), srs.getString("LOCATION"), srs.getString("DESCRIPTION")));	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return events; 
	}
	
	
	/**
	 * Method to view all events for REST service
	 * @return events - List<Event>: A list containing event objects for all events
	 */
	@Override
	public List<Event> viewAllREST() {
		
		// Creates SQL statement
		String sqlQuery = "SELECT * FROM EVENTS WHERE END_DATE_TIME >= '" + LocalDateTime.now() + "' ORDER BY START_DATE_TIME";
		
		// Creates an ArrayList of events that will be filled with all the events from the database
		List<Event> events = new ArrayList<Event>();
		
		try {
			// Accesses the database and query all events, and is given a results set with information of all events
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
			
			// Create a new event with all attributes and add to events
			while(srs.next()) {
				events.add(new Event(srs.getInt("ID"), srs.getInt("USER_ID"), srs.getString("TEXT"), srs.getTimestamp("START_DATE_TIME").toLocalDateTime(), 
						srs.getTimestamp("END_DATE_TIME").toLocalDateTime(), srs.getString("LOCATION"), srs.getString("DESCRIPTION")));	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return events; 
	}
	
	
	/**
	 * Method to view all events of a user for REST service
	 * @return events - List<Event>: A list containing event objects for all events
	 */
	@Override
	public List<Event> viewUserREST() {
		
		// Creates SQL statement
		String sqlQuery = "SELECT * FROM EVENTS WHERE USER_ID=? AND END_DATE_TIME >= '" + LocalDateTime.now() + "' ORDER BY START_DATE_TIME";
		
		// Creates an ArrayList of events that will be filled with all the events from the database
		List<Event> events = new ArrayList<Event>();
		
		try {
			// Accesses the database and query all events, and is given a results set with information of all events
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, session.getCurrentUser().getId());
			
			// Create a new event with all attributes and add to events
			while(srs.next()) {
				events.add(new Event(srs.getInt("ID"), srs.getInt("USER_ID"), srs.getString("TEXT"), srs.getTimestamp("START_DATE_TIME").toLocalDateTime(), 
						srs.getTimestamp("END_DATE_TIME").toLocalDateTime(), srs.getString("LOCATION"), srs.getString("DESCRIPTION")));	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return events; 
	}
	

	/**
	 * Method to view an event by the eventId
	 * @return eventId - Event: An object containing an event's information
	 */
	@Override
	public Event viewById(int eventId) {

		// Create SQL select statement
		String sqlQuery = "SELECT * FROM EVENTS WHERE ID = ?";
		
		// Initialize an event object to be with result of SQL statement
		Event event = new Event();

		try {
			// Accesses the database and query the matching event id, and is given a results set with the event information
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, eventId);

			//Loops through the results set
			while(srs.next()) {
				event = new Event(srs.getInt("ID"), srs.getInt("USER_ID"), srs.getString("TEXT"), srs.getTimestamp("START_DATE_TIME").toLocalDateTime(), 
						srs.getTimestamp("END_DATE_TIME").toLocalDateTime(), srs.getString("LOCATION"), srs.getString("DESCRIPTION"));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		return event;
	}
	
	
	/**
	 * setDataSouce takes in a DataSource from our web.xml in order to create a dataSource and JDBC Template Object used to 
	 * connect and perform CRUD action to the database
	 * @param ds - DataSource - to connect the sql command to the databases
	 */
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

}
