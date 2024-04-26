package edu.mu.finalproject.controller;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.EventManager;
import edu.mu.finalproject.view.EventView;

public class EventController {
	
	 public static Event addEvent() { //controller
		 
		 Event myEvent = EventView.createEvent();
		 
		 //If event didn't get created
		 if(myEvent == null) {
			 return null;
		 }
		
		 //If event is already added
		EventManager.getInstance();
		for(Event event : EventManager.getEventCollection()) {
			 if (event.equals(myEvent)) {
				 return null;
			 }
		 }
		 
		 
		//If event is added successfully
		 EventManager.getEventCollection().add(myEvent); 
		 EventFileReader.appendEvent(myEvent);
		 return myEvent;
		
	 }
	 
	 
	 
	 
	 
	 public static void getEventsToDispalyByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate) { //view
			
			ArrayList <Event> eventsInRange = new ArrayList<Event>();
			for(Event event : EventManager.getEventCollection()) {
				
				if(event.getEventYear().compareTo(startYear)>0 || (event.getEventYear().compareTo(startYear)==0 && event.getEventMonthDay().compareTo(startDate)>0)) {
					
					if(event.getEventYear().compareTo(endYear) < 0  ||  (event.getEventYear().compareTo(endYear)==0 && event.getEventMonthDay().compareTo(endDate) < 0) ) {
						eventsInRange.add(event);
					}
				}
			}
			
			EventView.displayEventsByDate(eventsInRange);
			return;
			
		 }
	 
	 
	 
	 
	 
	 public static int deleteEvent(Event eventToBeDeleted) { 
			
		 if(EventManager.getEventCollection().isEmpty()) {
			 return 0;
		 }
		 for(Event traverseEvent : EventManager.getEventCollection()) {
			 
			 if(traverseEvent.equals(eventToBeDeleted)) {
				 EventManager.getEventCollection().remove(eventToBeDeleted);
				 EventFileReader.deleteEvent(traverseEvent.getEventMonthDay(),
						 					 traverseEvent.getEventYear(),
						 					 traverseEvent.getArtistName()); //eventToBeDeleted is a "fake" event just for comparisons
				 return 1;
			 }
			
		 }
		 
		 return -1; 
	 }

}
