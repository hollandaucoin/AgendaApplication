package com.gcu.business;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Event;

public class EventBusinessService implements EventBusinessInterface {

	DataAccessInterface<Event> dao;
	
	@Autowired
	public void setUserService(DataAccessInterface<Event> dao) {
		this.dao = dao;
	}
	
	
	/**
	 * Method to get today's events by checking if each event is today
	 * @return todaysEvents - List<Event>: A list of event objects that match the current date
	 */
	@Override
	public List<Event> getTodaysEvents() {

		// Gets the full list of events
		List<Event> allEvents = dao.viewAll();
		
		// Create a list of events to add today's
		List<Event> todaysEvents = new ArrayList<Event>();
		
		// For loop to iterate through the list of events to get todays
		for(int i = 0; i < allEvents.size(); i ++) {
			if(allEvents.get(i).getStart().getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
				// Add events to list
				todaysEvents.add(allEvents.get(i));
			}
		}
		// Return the list of today's events
		return todaysEvents;
	}
	

	/**
	 * Method to get this week's events by checking if each event is the current week
	 * @return weekEvents - List<Event>: A list of event objects that match the current week
	 */
	@Override
	public List<Event> getWeekEvents() {
		
		// Gets the full list of events
		List<Event> allEvents = dao.viewAll();
		
		// Create a list of events to add this week
		List<Event> weekEvents = new ArrayList<Event>();
		
		// Get the day of the week as an integer
		DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
		int dayAsInt = dayOfWeek.getValue();
		
		// Set localDate variables for the week start and end dates
		LocalDateTime date1 = LocalDateTime.now().minusDays(dayAsInt);
		LocalDateTime date2 = LocalDateTime.now().plusDays(7 - dayAsInt);
		
		// For loop to iterate through the list of events to get this week
		for(int i = 0; i < allEvents.size(); i ++) {
			if(allEvents.get(i).getStart().isAfter(date1) && allEvents.get(i).getStart().isBefore(date2)) {
				// Add events to list
				weekEvents.add(allEvents.get(i));
			
			}
		}
		// Return the list of this weeks events
		return weekEvents;
	}

	
	/**
	 * Method to get this month's events by checking if each event is the current month
	 * @return monthEvents - List<Event>: A list of event objects that match the current month
	 */
	@Override
	public List<Event> getMonthEvents() {
		
		// Gets the full list of events
		List<Event> allEvents = dao.viewAll();
		
		// Create a list of events to add this month
		List<Event> monthEvents = new ArrayList<Event>();
		
		// For loop to iterate through the list of events to get this month
		for(int i = 0; i < allEvents.size(); i ++) {
			if(allEvents.get(i).getStart().getMonthValue() == LocalDateTime.now().getMonthValue()) {
				// Add events to list
				monthEvents.add(allEvents.get(i));
			}
		}
		// Return the list of this months events
		return monthEvents;
	}

	/**
	 * Method to get all events by calling the EventDataInterface.viewAll method
	 * @return allEvents - List<Event>: A list of event objects for all events
	 */
	@Override
	public List<Event> getAllEvents() {
		
		// Gets the full list of events
		List<Event> allEvents = dao.viewAll();
		
		return allEvents;
	}
	
	/**
	 * Method to get all events by calling the EventDataInterface.viewAllREST method
	 * @return allEvents - List<Event>: A list of event objects for all events
	 */
	@Override
	public List<Event> getAllEventsREST() {
		
		// Gets the full list of events
		List<Event> allEvents = dao.viewAllREST();
		
		return allEvents;
	}
	
	/**
	 * Method to get all events by calling the EventDataInterface.viewAllREST method
	 * @return allEvents - List<Event>: A list of event objects for all events
	 */
	@Override
	public List<Event> getUserEventsREST() {
		
		// Gets the full list of events
		List<Event> allEvents = dao.viewUserREST();
		
		return allEvents;
	}
	
	/**
	 * Method to add new event information by calling the EventDataInterface.create method
	 * @param event - Event: An event object
	 * @return Integer: An integer representing the status of the creation
	 */
	@Override
	public int addEvent(Event event) {

		return dao.create(event);
	}
	
	/**
	 * Method to edit an event's information by calling the EventDataInterface.update method
	 * @param event - Event: An event object
	 * @return Integer: An integer representing the status of the creation
	 */
	@Override
	public int editEvent(Event event) {

		return dao.update(event);
	}
	
	/**
	 * Method to remove an event by calling the EventDataInterface.delete method
	 * @param eventId - Integer: The ID of an event
	 * @return Integer: An integer representing the status of the creation
	 */
	@Override
	public int removeEvent(int eventId) {

		return dao.delete(eventId);
	}


	/**
	 * Method to view a event information by calling the EventDataInterface.viewById method
	 * @param eventId - Integer: The ID of an event
	 * @return Event: An event object for the user to view
	 */
	@Override
	public Event viewEvent(int eventId) {
		
		return dao.viewById(eventId);
	}

}
