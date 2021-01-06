package com.gcu.business;

import java.util.List;

import com.gcu.model.Event;

public interface EventBusinessInterface {
	
	/**
	 * See EventBusinessService.getTodaysEvents
	 */
	public List<Event> getTodaysEvents();
	
	/**
	 * See EventBusinessService.getWeekEvents
	 */
	public List<Event> getWeekEvents();
	
	/**
	 * See EventBusinessService.getMonthEvents
	 */
	public List<Event> getMonthEvents();
	
	/**
	 * See EventBusinessService.getAllEvents
	 */
	public List<Event> getAllEvents();
	
	/**
	 * See EventBusinessService.getAllEventsREST
	 */
	public List<Event> getAllEventsREST();
	
	/**
	 * See EventBusinessService.getUserEventsREST
	 */
	public List<Event> getUserEventsREST();
	
	/**
	 * See EventBusinessService.addEvent
	 */
	public int addEvent(Event event);
	
	/**
	 * See EventBusinessService.editEvent
	 */
	public int editEvent(Event event);
	
	/**
	 * See EventBusinessService.removeEvent
	 */
	public int removeEvent(int eventId);
	
	/**
	 * See EventBusinessService.viewEvent
	 */
	public Event viewEvent(int eventId);

}
