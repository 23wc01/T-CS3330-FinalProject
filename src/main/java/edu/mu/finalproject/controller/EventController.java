package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.view.EventView;

public class EventController {
	
	 public static int addEvent(Event myEvent) { //controller
		 
		
		 //If event is already added
		EventSingleton.getInstance();
		for(Event event : EventSingleton.getEventCollection()) {
			 if (event.equals(myEvent)) {
				 return 0;
			 }
		 }
		 
		 
		//If event is added successfully
		 EventSingleton.getEventCollection().add(myEvent); 
		 int result = EventFileReader.appendEvent(myEvent);
		 
		 if (result < 0) {
			 return -1; //If something went wrong in appendEvent
		 }
		 else {
			 return 1; //success
		 }

		
	 }
	 
	 
	 
	 
	 
	 public static ArrayList <Event> getEventsToDispalyByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate) { 
			
			ArrayList <Event> eventsInRange = new ArrayList<Event>();
			for(Event event : EventSingleton.getEventCollection()) {
				
				if(event.getEventYear().compareTo(startYear)>0 || (event.getEventYear().compareTo(startYear)==0 && event.getEventMonthDay().compareTo(startDate)>0)) {
					
					if(event.getEventYear().compareTo(endYear) < 0  ||  (event.getEventYear().compareTo(endYear)==0 && event.getEventMonthDay().compareTo(endDate) < 0) ) {
						eventsInRange.add(event);
					}
				}
			}
			
			return eventsInRange;
			
		 }
	 
	 
	 
	 

	 public static int deleteEvent(Event eventToBeDeleted) { 
			
		 if(EventSingleton.getEventCollection().isEmpty()) {
			 return 0;
		 }
		 for(Event traverseEvent : EventSingleton.getEventCollection()) {
			 
			 if(traverseEvent.equals(eventToBeDeleted)) {
				 EventSingleton.getEventCollection().remove(eventToBeDeleted);
				 int result = EventFileReader.deleteEventFromFile(traverseEvent.getEventMonthDay(),
						 					 traverseEvent.getEventYear(),
						 					 traverseEvent.getArtistName()); //eventToBeDeleted is a "fake" event just for comparisons
				 
				 if (result == -1) { //If an error occurred in deleteEventFromFile
					 return -1;
				 }
				 return 1;
			 }
			
		 }
		 
		 return -1; 
	 }

}
