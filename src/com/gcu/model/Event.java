package com.gcu.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Event {
	
	private int id;
	private int userId;
	private String color;
	
	// Variable to store the event name. Required and has size limit
	@NotNull(message="Event name is a required field.")
	@Size(min=2, max=25, message="Event name must be between 2 and 25 characters.\n")
	private String text;
	
	// Variable to store the start date and time of the event. Required and has a date format
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime start;
	
	// Variable to store the start date and time of the event. Required and has a date format
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime end;
	
	// Variable to store the event location. Required and has size limit
	@NotNull(message="Event location is a required field.")
	@Size(min=2, max=100, message="Event location must be between 2 and 100 characters.\n")
	private String location;
	
	// Variable to store the event description. Has a size limit
	@Size(min=0, max=500, message="Event description can only be 500 characters.\n")
	private String description;

	
	/**
	 * Empty constructor for an event
	 */
	public Event() {
		this.id = 0;
		this.userId = 0;
		this.text = "";
		this.start = null;
		this.end = null;
		this.color = "#f67944";
		this.location = "";
		this.description = "";
	}
	
	/**
	 * Constructor for an event
	 * @param id - Integer: The user's id
	 * @param name - String: The event name
	 * @param date - LocalDate: The event date
	 * @param time - LocalTime: The event time
	 * @param location - String: The event location
	 * @param description - String: The event description
	 */
	public Event(int id, int userId, String text, LocalDateTime start, LocalDateTime end, String location, String description) {
		this.id = id;
		this.text = text;
		this.start = start;
		this.end = end;
		this.color = "#f67944";
		this.location = location;
		this.description = description;
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
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId; 
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
