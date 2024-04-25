package edu.mu.finalproject.view;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventManager;
import edu.mu.finalproject.util.GetIntegerInput;

public class EventView {
	
	
	 public static void viewAddEvent() { 
		 
		 Event myEvent = EventController.addEvent();
		 
		 if(myEvent == null) {
			 System.out.println("You already added that event!");
			 return;
		 }
		 
		 else {
			  
			 System.out.println("Added new Event! " + myEvent.getArtistName() + " on " + myEvent.getEventMonthDay() + ", " + myEvent.getEventYear());
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
	 
	 
	 
	 
	 
	 
	 
	 public static void getEventDisplayInfo() { 
			
		 if(EventManager.getEventCollection().isEmpty()) {
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
		 EventController.getEventsToDispalyByDate(yearStart, monthdayStart, yearEnd, monthdayEnd);
		 
		 }
		 
		 
		 catch(Exception e){
			 System.out.println("Type mismatch. Please enter a valid date. Valid dates have four integers for the year, and 1-2 integers for the month and day");
			 
		 }
		 
	 }
	 
	 
	 
	 
	 public static void displayEventsByDate(ArrayList <Event> eventsInRange) {
			
		 	if(eventsInRange.isEmpty()) {
		 		System.out.println("No events in date range...");
		 	}
		 	
		 	
			for(Event event : eventsInRange) {
				
				System.out.println(event.toString());
				
			}
			
			System.out.println();
			
		 }
	 
	 
	 
	 public static void viewDeleteEvent() { 
			
		 if(EventManager.getEventCollection().isEmpty()) {
			 System.out.println("You have no events!");
			 return;
		 }
		 
		System.out.println("\nEnter information so we can find the event to delete\n ");
		Event eventToBeDeleted = createEvent();
		 
		int result = EventController.deleteEvent(eventToBeDeleted);
		
		if (result > 0) {
			System.out.println("Event successfully deleted!");
		}
		else {
			 System.out.println("No event with that information found");
		}
			
		 	 
	 }


	 
	 
	 
	 

}
