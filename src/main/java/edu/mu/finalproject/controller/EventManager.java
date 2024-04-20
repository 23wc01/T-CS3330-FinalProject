package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.*;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.util.GetIntegerInput;


public class EventManager {

	private static EventManager instance;
	public static ArrayList <Event> eventCollection = new ArrayList<Event>();
	
	//private constructor to prevent instantiation
	private EventManager() {
	}
	
	//Static method to provide access to the single instance
	 public static EventManager getInstance() {
	 
	 	/*
	 	 * create instance if it doesn't exist
	 	 */
	 	if (instance == null){
	 		instance = new EventManager();
	 	}
	 	
	 	return instance;
	 }
	 
	 
	 
//------------------------------------------------------------------------------------	 
	 
	 public void addEvent() {
		 
		 Event myEvent = createEvent();
		 
		 if(myEvent == null) {
			 return;
		 }
		 
		 for(Event event : eventCollection) {
			 if (event.equals(myEvent)) {
				 System.out.println("You already added that event!");
				 return;
			 }
		 }
		 
		 eventCollection.add(myEvent); 
		 EventFileReader.appendEvent(myEvent);
		 System.out.println("Added new Event! " + myEvent.getArtistName() + " on " + myEvent.getEventMonthDay() + ", " + myEvent.getEventYear());
	 }
	 
	 
	 
	 
	 private static Event createEvent() {
		 
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
		 return myEvent;
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 
		 }
		 finally {
			 //scanner.close(); Doing this closes System.in
		 }
		 
		System.out.println("Failed to create event.");
		return null;
	 }
	 
	 
	 
//-------------------------------------------------------------------------------------------
	 
	 public void displayEventsByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate) {
		
		 int printCount = 0;
		for(Event event : eventCollection) {
			
			if(event.getEventYear().compareTo(startYear)>0 || (event.getEventYear().compareTo(startYear)==0 && event.getEventMonthDay().compareTo(startDate)>0)) {
				
				if(event.getEventYear().compareTo(endYear) < 0  ||  (event.getEventYear().compareTo(endYear)==0 && event.getEventMonthDay().compareTo(endDate) < 0) ) {
					System.out.println(event.toString());
					printCount++;
				}
			}
		}
		if(printCount == 0) {
			System.out.println("No events in date range...");
		}

		
		
		System.out.println();
		
	 }
	 
	 public void getEventDisplayInfo() {
		
		 if(eventCollection.isEmpty()) {
				System.out.println("You have no events!");
				return;
			}
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 try {

		//Year Start--------------
		 System.out.print("What year do you want to start your display from? ");
		 int yearNumberStart = GetIntegerInput.getInputInteger(scanner);
		 Year yearStart = Year.of(yearNumberStart);
		 scanner.nextLine();
		 
		 //Month Start------------
		 System.out.print("What month do you want to start your display from? ");
		 int monthNumberStart = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		 //Day Start--------------
		 System.out.print("What day do you want your display to start from? ");
		 int dayNumberStart = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		 
		//Year End--------------
		 System.out.print("What year do you want your display to end on? ");
		 int yearNumberEnd = GetIntegerInput.getInputInteger(scanner);
		 Year yearEnd = Year.of(yearNumberEnd);
		 scanner.nextLine();
		 
		 //Month End------------
		 System.out.print("What month do you want your display to end on? ");
		 int monthNumberEnd = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		 //Day End--------------
		 System.out.print("What day do you want your display to end on? ");
		 int dayNumberEnd = GetIntegerInput.getInputInteger(scanner);
		 scanner.nextLine();
		 
		
		 
		 //MonthDay---------
		 MonthDay monthdayStart = MonthDay.of(monthNumberStart, dayNumberStart);
		 MonthDay monthdayEnd = MonthDay.of(monthNumberEnd, dayNumberEnd);
		 
	
		 
		 //Using the information to call displayEvent
		 displayEventsByDate(yearStart, monthdayStart, yearEnd, monthdayEnd);
		 
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 
		 }
		 
	 }

	 
	 
//-----------------------------------------------------------------------------------
	 

	 
	 public void deleteEvent() {
		
		 if(eventCollection.isEmpty()) {
			 System.out.println("You have no events!");
			 return;
		 }
		 
		System.out.println("\nEnter information so we can find the event to delete\n ");
		Event eventToBeDeleted = createEvent();
		 
		 for(Event traverseEvent : eventCollection) {
			 
			 if(traverseEvent.equals(eventToBeDeleted)) {
				 eventCollection.remove(eventToBeDeleted);
				 EventFileReader.deleteEvent(traverseEvent.getEventMonthDay(),
						 					 traverseEvent.getEventYear(),
						 					 traverseEvent.getArtistName()); //eventToBeDeleted is a "fake" event just for comparisons
				 return;
			 }
			
		 }
		 
		 System.out.println("No event with that information found"); 
	 }
	 
}
