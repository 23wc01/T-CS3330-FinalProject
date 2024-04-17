package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.*;
import edu.mu.finalproject.model.Event;


public class EventManager {

	private static EventManager instance;
	ArrayList <Event> eventCollection = new ArrayList<Event>();
	
	//private constructor to prevent instantiation
	private EventManager() {
	}
	
	//Static method to provide access to the single instance
	 public static EventManager getInstance() {
	 
	 	//create instance if it doesn't exist
	 	if (instance == null){
	 		instance = new EventManager();
	 	}
	 	
	 	return instance;
	 }
	 
	 public void addEvent(Event myEvent) {
		 // Code here
	 }
	 
	 public void deleteEvent(Event myEvent) {
		 // Code here
	 }
		
	 public void displayEventsByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate) {
		 // Code here
	 }
}
