package edu.mu.finalproject.view;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.util.GetIntegerInput;

public class EventView {
	
	
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
	 
	 
	 
	
	 public static Event createEvent() { 
			 
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
	 
	 
	 
	 
	 
	 
	 
	 public static int getEventDisplayInfo() { 
			
		 if(EventSingleton.getEventCollection().isEmpty()) {
				displayEventsByDate(null);
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
		 ArrayList <Event> eventsInRange = EventController.getEventsToDispalyByDate(yearStart, monthdayStart, yearEnd, monthdayEnd);
		 displayEventsByDate(eventsInRange);
		 return 1;
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 return -1;
		 }
		 
	 }
	 
	 
	 
	 
	 public static int displayEventsByDate(ArrayList <Event> eventsInRange) {
		 
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
	 
	 
	 
	 public static int viewDeleteEvent(Event eventToBeDeleted) { 
			
		 
		int result = EventController.deleteEvent(eventToBeDeleted);
		
		if (result > 0) {
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
