package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.view.EventView;
/**
 * The EventController class handles logic related to Events, including adding, deleting, and displayng Event Objects
 * 
 * Interacts closely with Event, EventFileReader, and EventView
 * 
 * @author etwil
 */
public class EventController {
	
	static EventSingleton theEventSingleton = EventSingleton.getInstance();
	
	 /**
	  * Adds an Event to the event collection and writes it to the file
	  * @param myEvent
	  * @return an integer representing result of operation
	  *     0 if the event has already been added
	  *     1 if successfully added event
	  *    -1 if something went wrong appending the event to a file
	  * 
	  * @author etwil
	  */
	 public static int addEvent(Event myEvent) { //controller
		 
		
		 //If event is already added
		EventSingleton.getInstance();
		for(Event event : theEventSingleton.getEventCollection()) {
			 if (event.equals(myEvent)) {
				 return 0;
			 }
		 }
		 
		 
		//If event is added successfully
		 theEventSingleton.getEventCollection().add(myEvent); 
		 int result = EventFileReader.appendEvent(myEvent);
		 
		 if (result < 0) {
			 return -1; //If something went wrong in appendEvent
		 }
		 else {
			 return 1; //success
		 }

		
	 }
	 
	 
	 
	 
	 /**
	  * Iterates through the events collection and adds all events within a specified date range to an array.
	  * @param startYear
	  * @param startDate
	  * @param endYear
	  * @param endDate
	  * @param catalog - collection of events
	  * @return ArrayList eventsInRange - The events in the date range
	  * @author etwil
	  */
	 public ArrayList <Event> getEventsToDispalyByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate, ArrayList<Event> catalog) { 
			
			ArrayList <Event> eventsInRange = new ArrayList<Event>();
			for(Event event : catalog) { // For each event in catalog, check if it is in the specified date range
				
				if(event.getEventYear().compareTo(startYear)>0 || (event.getEventYear().compareTo(startYear)==0 && event.getEventMonthDay().compareTo(startDate)>0)) {
					
					if(event.getEventYear().compareTo(endYear) < 0  ||  (event.getEventYear().compareTo(endYear)==0 && event.getEventMonthDay().compareTo(endDate) < 0) ) {
						eventsInRange.add(event);
					}
				}
			}
			
			
			return eventsInRange;
			
		 }
	 
	 
	 
	 
	 /**
	  * Attempts to delete an event from the catalog. First checks if the collection is empty.
	  * Then iterates through the collection to find a matching Event, upon which it removes 
	  * the Event from the collection Array and the file
	  * @param eventToBeDeleted
	  * @return an integer representing the result of the operation
	  *     0 for an empty collection
	  *     1 for success
	  *     -1 for no event found
	  *  @author etwil
	  */
	 public static int deleteEvent(Event eventToBeDeleted) { 
			
		 if(theEventSingleton.getEventCollection().isEmpty()) {
			 return 0;
		 }
		 for(Event traverseEvent : theEventSingleton.getEventCollection()) {
			 
			 if(traverseEvent.equals(eventToBeDeleted)) {
				 theEventSingleton.getEventCollection().remove(eventToBeDeleted);
				 EventFileReader.deleteEventFromFile(traverseEvent.getEventMonthDay(),
						 					 traverseEvent.getEventYear(),
						 					 traverseEvent.getArtistName()); //eventToBeDeleted is a "fake" event just for comparisons
				 

				 return 1;
			 }
			
		 }
		 
		 return -1; 
	 }

}
