package edu.mu.finalproject.model;

import java.util.*;


public class EventSingleton {

	private static EventSingleton instance;
	public static ArrayList <Event> eventCollection = new ArrayList<Event>();
	
	//private constructor to prevent instantiation
	private EventSingleton() {
	}
	
	//Static method to provide access to the single instance
	 public static EventSingleton getInstance() {
	 
	 	/*
	 	 * create instance if it doesn't exist
	 	 */
	 	if (instance == null){
	 		instance = new EventSingleton();
	 	}
	 	
	 	return instance;
	 }
	 
	 public ArrayList<Event> getEventCollection() {
			return eventCollection;
		}

	public static void setEventCollection(ArrayList<Event> eventCollection) {
			EventSingleton.eventCollection = eventCollection;
		}
	 
	 
}
