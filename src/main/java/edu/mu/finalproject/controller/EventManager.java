package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.*;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.util.GetIntegerInput;


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
	 
	 
	 
	 
	 
	 public void addEvent() {
		 
		 Event myEvent = createEvent();
		 
		 for(Event event : eventCollection) {
			 if (event.equals(myEvent)) {
				 System.out.println("You already added that event!");
				 return;
			 }
		 }
		 
		 eventCollection.add(myEvent); 
	 }
	 
	 
	 
	 
	 private Event createEvent() {
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 try {
		//Year--------------
		 System.out.print("What year does this event take place? ");
		 int yearNumber = GetIntegerInput.getInputInteger(scanner);
		 Year year = Year.of(yearNumber);
		 scanner.nextLine();
		 
		 //Month------------
		 System.out.print("What month does this event take place? ");
		 int monthNumber = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		 //Day--------------
		 System.out.print("What day does this event take place? ");
		 int dayNumber = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		 //Artist-----------
		 System.out.print("What artist is this event for? ");
		 String artistName = scanner.nextLine();
		 
		 //MonthDay---------
		 MonthDay monthday = MonthDay.of(monthNumber, dayNumber);
		 
	
		 
		 //Using the information to make an event
		 Event myEvent = new Event(monthday, year, artistName);
		 System.out.println("Added new Event! " + myEvent.getArtistName() + " on " + myEvent.getEventMonthDay() + ", " + myEvent.getEventYear());
		 return myEvent;
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 
		 }
		 finally {
			 scanner.close();
		 }
		 
		System.out.println("Failed to create event.");
		return null;
	 }
	 
	 
	 
	 
	 public void deleteEvent(Event myEvent) {
		 // Code here
	 }
		
	 public void displayEventsByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate) {
		 // Code here
	 }
}
