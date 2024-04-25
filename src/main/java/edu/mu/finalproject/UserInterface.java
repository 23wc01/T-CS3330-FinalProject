package edu.mu.finalproject;

import java.util.ArrayList;
import java.util.Scanner;
import edu.mu.finalproject.controller.*;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.controller.EventManager;


public class UserInterface {
	
	public static EventManager TheEventManager;
	public static FavoritesManager TheFavoritesManager;
	//fields to hold catalogs go here
	//Any other fields go here (user?)
	
	//Constructor
	public UserInterface() {
		
		TheEventManager = EventManager.getInstance(); //Create an instance of EventManager
		TheFavoritesManager = FavoritesManager.getInstance(); //Create an instance of Favorites Manager
		//Create a music catalog
		
	}
	
	
	public void start() {
		EventManager.eventCollection = EventFileReader.readEvents();
		//Read any other files here
		run();
	}
	
	//Waits for a valid input and runs the method that aligns with what the user wants.
	public void run() {
		
		System.out.println("Welcome to Media Tracker! Type your command, or type \"menu\" to see options.");
		Scanner scanner = new Scanner(System.in);
	    String userInput;
		
	
	    // Continuously prompt the user until a valid input is entered
	    boolean flag = true;
	    while (flag == true) {
		    
	        System.out.print("Enter a command: ");
	        userInput = scanner.nextLine().trim(); // Trim any whitespace
	
	        ECommands command = getCommand(userInput);
	        
	        // Check the user input and call the corresponding method
	        switch (command) {
            case MENU:
                UIExecutionMethods.executeMenu();
                break;
            case ADD_EVENT:
                UIExecutionMethods.executeAddEvent();
                break;
            case DELETE_EVENT:
            	UIExecutionMethods.executeDeleteEvent();
            	break;
            case DISPLAY_EVENTS:
            	UIExecutionMethods.executeDisplayEvents();
            	break;
//WAITING FOR CATALOG:
//            case FAVORITE:
//            	UIExecutionMethods.executeFavorite();
//            	break;
            case SETUP_PREFERENCE:
            	UIExecutionMethods.excuteSetupPreference();
            	break;
                
              //!!!PUT YOUR NEW COMMAND CASE HERE (FIND TEMPLATE BELOW)!!!
            
            	
            case EXIT:
                UIExecutionMethods.executeExit();
                flag = false;
                break;
            case INVALID:
                System.out.println("Invalid command. Please try again.");
                break;
	        }
	        
		    
	    }
	    scanner.close();
	
	}
	
	
	//Helper for getting command in enum form
	private ECommands getCommand(String userInput) {
		try {
			return ECommands.valueOf(userInput.toUpperCase());
		} 
		catch (IllegalArgumentException e) {
			return ECommands.INVALID;
		}
	}

	
	
}




/*
 * TEMPLATE AND INSTRUCTIONS FOR USE
 * 
 * Instructions: 
 * - First, add your command to the enum (See commands enum for further details)
 * - Put the switch case with your corresponding information in the while loop
 * - Put your executeyourmethod method in UIExecutionMethods.java 
 * 
 * Template for copy/paste
 * 
   case YOUR_COMMAND:
                UIExecutionMethods.executeYourMethod();
                break;
	        
	        

	        */


