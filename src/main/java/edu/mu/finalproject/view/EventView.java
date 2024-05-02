package edu.mu.finalproject.view;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.util.GetIntegerInput;
/**
 * The EventView class handles receiving input from a user, as well as displaying results of operations
 * relating to Event.
 * 
 * Provides methods for viewing the results of adding and deleting an event, and viewing a display based on the user input
 * Methods also exists for collecting bounds for the display from the user, as well as an interface for creating an event object.
 * 
 * Accesses the eventCollection through EventSingleton
 * Works closely with EventController and UIExecutionMethods
 */
public class EventView {
	
	//Instantiation
	static EventSingleton theEventSingleton = EventSingleton.getInstance();
	
	/**
	 * Attempts to add an Event to the event catalog and provides feedback through the console
	 * It first checks that the provided event is not null, which would indicate a failure in event creation
	 * It tries to add event to the catalog
	 * 
	 * @param myEvent - event to add
	 * @return an integer representing the result of the operation
	 * 		-1 for errors or if event is null
	 * 		0 if the event is already added
	 * 		1 for success
	 * 
	 * @author etwil
	 */
	 public static int viewAddEvent(Event myEvent) { 
		 
		 if(myEvent == null) {
			 System.out.println("Something went wrong..."); //To get here, something would have needed to fail in createEvent
			 return -1;
		 }
		 
		
		 int result = EventController.addEvent(myEvent);
		 
		 
		 if(result == 0) { //Already has event
			 System.out.println("You already added that event!");
			 return 0;
		 }
		 
		 else if(result < 0){ //FileReader fail
			 System.out.println("We couldn't add your item to the catalog");
			 return -1;
		 }
		 
		 else { //Success
			  
			 System.out.println("Added new Event! " + myEvent.getArtistName() + " on " + myEvent.getEventMonthDay() + ", " + myEvent.getEventYear());
			 return 1;
		 }
		 
		 
	 }
	 
	 
	 
	/**
	 * Gets information for the event from the user, and calls the Event constructor to make a new event.
	 * Used for adding and event, and is part of the logic for deleting an event.
	 * Uses GetInutInteger from the class of the same name.
	 * 
	 * Catches type errors when an invalid date is entered
	 * 
	 * @return Event made with the parameters specified by user
	 * 			Event myEvent
	 * @author etwil
	 */
	 public Event createEvent() { 
			 
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
			 
			System.out.println("Failed to create event.");
			return null;
		 }
	 
	 
	 
	 
	 
	 
	 /**
	  * Gets information about the bounds of the display from the user. Gets the date to start the display from, and the date to end the display on.
	  * 
	  * Calls getEventsToDisplayByDate from EventController 
	  * Calls displayEventsByDate in this class 
	  * 
	  * @param ArrayList eventCatalog
	  * @return integer representing success or failure
	  * 		-1 for invalid dates
	  * 		 1 for success
	  * @author etwil
	  */
	 public int getEventDisplayInfo(ArrayList<Event>catalog) {
		 
		 EventView eventview = new EventView();
			
		 if(theEventSingleton.getEventCollection().isEmpty()) {
				eventview.displayEventsByDate(null);
				return 0;
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
		 
	
		 
		 //Using the information to call displayEvent and displayEventsInRange
		 EventController eventcontroller = new EventController();
		 ArrayList <Event> eventsInRange = eventcontroller.getEventsToDispalyByDate(yearStart, monthdayStart, yearEnd, monthdayEnd, catalog);
		 eventview.displayEventsByDate(eventsInRange);
		 return 1;
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 return -1;
		 }
		 
	 }
	 
	 
	 
	 /**
	  * Given an array of events, this method prints those events to the console, or prints a message if no events were found.
	  * @param ArrayList eventsInRange - Array of all the events in the range specified by getEventDisplayInfo
	  * @return integer representing result of operation
	  * 		0 for if there are no events in the events collection
	  * 		-1 for if there were no events in range
	  * 		1 for success
	  * @author etwil
	  */
	 public int displayEventsByDate(ArrayList <Event> eventsInRange) {
		 
		 	if(eventsInRange == null) {
		 		System.out.println("You have no events!");
		 		return 0;
		 	}
			
		 	if(eventsInRange.isEmpty()) {
		 		System.out.println("No events in date range...");
		 		return -1;
		 	}
		 	
		 	
			for(Event event : eventsInRange) {
				
				System.out.println(event.toString());
				
			}
			
			System.out.println();
			return 1;
			
		 }
	 
	 
	 /**
	  * This method prints the result of the delete operation to the console. 
	  * @param Event eventToBeDeleted
	  * @return integer representing result of the operation
	  * 		0 for no events exist in the collection
	  * 		-1 for event not found
	  * 		1 for success
	  * @author etwil
	  */
	 public static int viewDeleteEvent(Event eventToBeDeleted) { 
			
		 
		int result = EventController.deleteEvent(eventToBeDeleted);
		
		if (result > 0) {
			System.out.println("" + eventToBeDeleted.getEventMonthDay() +" "+ eventToBeDeleted.getEventYear() +" "+ eventToBeDeleted.getArtistName());
			System.out.println("Event successfully deleted!");
			return result;
		}
		else if (result == 0) {
			System.out.println("You have no events!");
			return result;
		}
		else {
			 System.out.println("No event with that information found");
			 return result;
		}
			
		 	 
	 }


	 
	 
	 
	 

}
