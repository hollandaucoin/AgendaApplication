package com.gcu.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.EventBusinessInterface;
import com.gcu.model.Event;

@Controller
@RequestMapping("/events")
public class EventController {
	
	private EventBusinessInterface service;
	
	@Autowired
	public void setUserService(EventBusinessInterface service) {
		this.service = service;
	}
	
	/**
	 * Method to show the homePage.jsp (calendar), passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/calendar", method = RequestMethod.GET)
	public ModelAndView displayHomePage() {
		
		try {
			return new ModelAndView("homePage", "event", new Event());
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}

	}
	
	/**
	 * Method to show the todaysEventPage.jsp, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/today", method = RequestMethod.GET)
	public ModelAndView displayTodaysEventPage() {
		
		try {
			// Create day of week string
			String day = LocalDate.now().getDayOfWeek().name();
			day = day.charAt(0) + day.substring(1).toLowerCase();
			
			// Create current month string
			String month = LocalDate.now().getMonth().toString();
			month = month.charAt(0) + month.substring(1).toLowerCase();
			
			// Create date title
			String title = day + ", " + month + " " + LocalDate.now().getDayOfMonth();
			
			// Create a list and fill it with todays events by calling method in business service
			List<Event> events = new ArrayList<Event>();
			events = service.getTodaysEvents();
			
			// If there are no events that day, display message
			if(events.isEmpty()) {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and a message that there are no events today
				mv.addObject("title", title);
				mv.addObject("message", "No upcoming events today.");
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
	
				return mv;
			}
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and events
				mv.addObject("title", title);
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
				
				return mv;
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	
	/**
	 * Method to show the weekEventPage.jsp, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public ModelAndView displayWeekEventPage() {
		
		try {
			// Get the day of the week as an integer
			DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
			int dayAsInt = dayOfWeek.getValue();
			
			// Set localDate variables for the week start and end dates
			LocalDateTime date1 = LocalDateTime.now().minusDays(dayAsInt);
			LocalDateTime date2 = LocalDateTime.now().plusDays(7 - dayAsInt);
			
			// Create first date
			String month1 = date1.getMonth().toString();
			month1 = month1.charAt(0) + month1.substring(1).toLowerCase();
			int day1 = date1.getDayOfMonth();
			
			// Create second date
			String month2 = date2.getMonth().toString();
			month2 = month2.charAt(0) + month2.substring(1).toLowerCase();
			int day2 = date2.getDayOfMonth();
				
			// Create date title
			String title = month1 + " " + day1 + " - " + month2 + " " + day2;
			
			// Create a list and fill it with this week's events by calling method in business service
			List<Event> events = new ArrayList<Event>();
			events = service.getWeekEvents();
			
			// If there are no events that day, display message
			if(events.isEmpty()) {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing a message that there are no events this week
				mv.addObject("title", title);
				mv.addObject("message", "No upcoming events this week.");
				mv.setViewName("listEventPage");
				mv.addObject("events", events);
	
				return mv;
			}
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and events
				mv.addObject("title", title);
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
				
				return mv;
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	
	/**
	 * Method to show the monthEventPage.jsp, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/month", method = RequestMethod.GET)
	public ModelAndView displayMonthEventPage() {
		
		try {
			// Create month string for title
			String title = LocalDate.now().getMonth().toString();
			title = title.charAt(0) + title.substring(1).toLowerCase();
			
			// Create a list and fill it with this months upcoming events by calling method in business service
			List<Event> events = new ArrayList<Event>();
			events = service.getMonthEvents();
			
			// If there are no upcoming events this month, display message
			if(events.isEmpty()) {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing a message that there are no events this month
				mv.addObject("title", title);
				mv.addObject("message", "No upcoming events this month.");
				mv.setViewName("listEventPage");
				mv.addObject("events", events);
	
				return mv;
			}
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and events
				mv.addObject("title", title);
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
				
				return mv;
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Method to show the allEventPage.jsp, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ModelAndView displayAllEventsPage() {
		
		try {
			// Create a list and fill it with all upcoming events by calling method in business service
			List<Event> events = new ArrayList<Event>();
			events = service.getAllEvents();
			
			// If there are no upcoming events, display message
			if(events.isEmpty()) {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing a message that there are no events
				mv.addObject("title", "All Upcoming Events");
				mv.addObject("message", "No upcoming events.");
				mv.setViewName("listEventPage");
				mv.addObject("events", events);
	
				return mv;
			}
			else {
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and events
				mv.addObject("title", "All Upcoming Events");
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
				
				return mv;
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	

	@RequestMapping(path="/viewEvent", method=RequestMethod.POST)
	public ModelAndView viewEvent(int eventId) {

		try {
			Event event = new Event();
			event = service.viewEvent(eventId);

			return new ModelAndView("viewEvent", "event", event);
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	
	
	/**
	 * Method to show the newEventPage.jsp form, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView displayNewEventForm() {
		
		try {
			return new ModelAndView("newEventPage", "event", new Event());
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Method to submit a new event
	 * @param event - Event
	 * @param result - BindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/newEvent", method = RequestMethod.POST)
	public ModelAndView submitNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		
		try {
			// Validate the form submitted, if it has errors send back to newEventPage
			if(result.hasErrors() == true) {
	
				return new ModelAndView("newEventPage", "event", event);
			}
			// The form doesn't have errors
			else {
				// Verify if the input was correct through calling method in business interface
				if(service.addEvent(event) == 1) {
	
					// Send to homePage
					System.out.println("ID:" + event.getId());
					return new ModelAndView("viewEvent", "event", event);
				}
				// The start date is prior to now, send an error message
				else if (service.addEvent(event) == -1) {

					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The start date and time has already passed, please try again.<br><br>");
					mv.setViewName("newEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The end date is prior to now, send an error message
				else if (service.addEvent(event) == -2) {

					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The end date and time has already passed, please try again.<br><br>");
					mv.setViewName("newEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The end date is prior to the start date, send an error message
				else if (service.addEvent(event) == -3) {

					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The end date and time is before the start date and time, please try again.<br><br>");
					mv.setViewName("newEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The input was incorrect
				else {

					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "Invalid event fields, please try again.<br><br>");
					mv.setViewName("newEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	
	
	/**
	 * Method to show the editEventPage.jsp form, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public ModelAndView displayEditEventForm(int eventId) {

		try {
			Event event = new Event();
			event = service.viewEvent(eventId);

			return new ModelAndView("editEventPage", "event", event);
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Method to submit an edit of an event
	 * @param event - Event
	 * @param result - BindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/editEvent", method = RequestMethod.POST)
	public ModelAndView submitEditEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		
		try {
			// Validate the form submitted, if it has errors send back to newEventPage
			if(result.hasErrors() == true) {
	
				return new ModelAndView("editEventPage", "event", event);
			}
			// The form doesn't have errors
			else {
				// Verify if the edit was successful through calling method in business interface
				if(service.editEvent(event) == 1) {
	
					// Send to homePage
					return new ModelAndView("viewEvent", "event", event);
				}
				// The start date is prior to now, send an error message
				else if (service.editEvent(event) == -1) {
					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The start date and time has already passed, please try again.<br><br>");
					mv.setViewName("editEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The end date is prior to now, send an error message
				else if (service.editEvent(event) == -2) {
					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The end date and time has already passed, please try again.<br><br>");
					mv.setViewName("editEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The end date is prior to the start date, send an error message
				else if (service.editEvent(event) == -3) {
					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the newEventPage
					mv.addObject("message", "The end date and time is before the start date and time, please try again.<br><br>");
					mv.setViewName("editEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
				// The edit failed
				else {
					// Create a new ModelAndView object to return
					ModelAndView mv = new ModelAndView();
					// Set the ModelAndView to have an object containing an invalid event fields message and send back to the editEventPage
					mv.addObject("message", "Invalid event fields, please try again.<br><br>");
					mv.setViewName("editEventPage");
					mv.addObject("event", event);
	
					return mv;
				}
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	}
	
	
	/**
	 * Method to delete an event and then send the user back to the homePage.jsp form, passing the view name, model name, and object
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/deleteEvent", method = RequestMethod.POST)
	public ModelAndView deleteEvent(int eventId, String title) {

		try {
			
			// Verify if the deletion was successful through calling method in business interface
			if(service.removeEvent(eventId) == 1) {
				
				// Create a list and fill it with all upcoming events by calling method in business service
				List<Event> events = new ArrayList<Event>();
				if(title.contains(",")) {
					events = service.getTodaysEvents();
				}
				else if(title.contains("-")) {
					events = service.getWeekEvents();
				}
				else if(title.contains("All")) {
					events = service.getAllEvents();
				}
				else {
					events = service.getMonthEvents();
				}
				
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing the title and events
				mv.addObject("title", title);
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
				
				return mv;
			}
			// The deletion failed
			else {
				
				// Create a list and fill it with all upcoming events by calling method in business service
				List<Event> events = new ArrayList<Event>();
				if(title.contains(",")) {
					events = service.getTodaysEvents();
				}
				else if(title.contains("-")) {
					events = service.getWeekEvents();
				}
				else if(title.contains("All")) {
					events = service.getAllEvents();
				}
				else {
					events = service.getMonthEvents();
				}
				
				// Create a new ModelAndView object to return
				ModelAndView mv = new ModelAndView();
				// Set the ModelAndView to have an object containing an invalid event fields message and send back to the listEventPage
				mv.addObject("error", "Deletion failed, please try again.<br><br>");
				mv.addObject("title", title);
				mv.addObject("events", events);
				mv.setViewName("listEventPage");
	
				return mv;
			}
		}
		catch(Exception e) {
			return new ModelAndView("errorPage");
		}
	
	}
}
