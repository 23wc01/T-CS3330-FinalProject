package edu.mu.finalproject.model;

import java.util.*;


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
	 
	 public static ArrayList<Event> getEventCollection() {
			return eventCollection;
		}

	public static void setEventCollection(ArrayList<Event> eventCollection) {
			EventManager.eventCollection = eventCollection;
		}
	 
	 
}
