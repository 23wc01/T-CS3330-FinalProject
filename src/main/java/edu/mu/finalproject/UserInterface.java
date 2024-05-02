package edu.mu.finalproject;

import java.util.Scanner;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.model.CatalogSingleton;

/**
 * The UserInterface class handles starting the program, reading the user input from the console,
 * and directing the flow of control to the proper execution method based on the input
 * 
 * This class works closely with UIExecutionMethods class as it sends the flow of control to that class.
 * This class works  closely with ECommands in matching user input with commands
 */

public class UserInterface {
	
	public static EventSingleton TheEventManager;
	public static CatalogSingleton TheCatalogSingleton;


	private Account user;
	
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}


	/**
	 * Constructor for UserInterface, called in main
	 * @param none
	 * 
	 */
	public UserInterface() {
		TheEventManager = EventSingleton.getInstance(); //Create an instance of EventManager
		TheCatalogSingleton = TheCatalogSingleton.getInstance();
	}
	
	/**
	 * Handles anything that needs to be done to set up for the program, like file reading
	 * Starts the main portion of the program.
	 * @param none
	 */
	public void start() {
		EventSingleton.setEventCollection(EventFileReader.readEvents(null)); 
		//Read any other files here
		run();
	}
	
	/**
	 * Waits for a valid input and runs the method that aligns with what the user wants.
	 * @param none
	 * @return none
	 */
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
//            case DISPLAY_FAVORITES:
//                UIExecutionMethods.executeDisplayFavorites();
//                break;
//WAITING FOR CATALOG:
//            case FAVORITE:
//            	UIExecutionMethods.executeFavorite();
//            	break;
// WAITING FOR ACCOUNT
            case SEARCH:
 				UIExecutionMethods.executeSearchCatalog(); 
 				break; 
            case SETUP_PREFERENCE:
            	UIExecutionMethods.executeSetupPreference(user);
            	break;
            case RECOMMEND_PLAYLIST:
 				UIExecutionMethods.executeRecommendPlaylist(user); 
 				break; 
  			case DOWNLOAD_RECOMMENDED_PLAYLIST:
  				UIExecutionMethods.executeDownloadRecommendedPlaylist(user); 
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
	   
	
	}
	
	
	/**
	 * Helper for getting command in enum form to use in the switch case
	 * @param none
	 * @return Ecommands Command to be used in switch case
	 * @author etwil
	 */
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


