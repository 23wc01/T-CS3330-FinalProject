package edu.mu.finalproject;

import java.util.Scanner;
import edu.mu.finalproject.controller.*;

//DO NOT USE THIS YET
public class UserInterface {
	
	public static EventManager TheEventManager;
	//fields to hold catalogs go here
	//Any other fields go here (user?)
	
	//Constructor
	public UserInterface() {
		
		TheEventManager = EventManager.getInstance(); //Create an instance of EventManager
		//Create a music catalog
		
	}
	
	
	//Waits for a valid input and runs the method that aligns with what the user wants.
	public void start() {
		
		System.out.println("Welcome to Media Tracker! Type your command, or type \"menu\" to see options.");
		Scanner scanner = new Scanner(System.in);
	    String userInput;
		
	
	    // Continuously prompt the user until a valid input is entered
	    while (true) {
		    
	        System.out.print("Enter a command: ");
	        userInput = scanner.nextLine().trim(); // Trim any whitespace
	
	        
	        
	        // Check the user input and call the corresponding method
	        if(userInput.equalsIgnoreCase("menu")) {
	        	 executeAddEvent();
		           break; 
	        } 
	        else if (userInput.equalsIgnoreCase("add event")) {
	            executeAddEvent();
	           
	        } 
	        
	        
	        //!!!PUT YOUR NEW COMMAND CASE HERE (FIND TEMPLATE BELOW)!!!
	        
	        // To end program
	        else if (userInput.equalsIgnoreCase("exit")) {
	            // We could provide an exit method that cleans things up, saves catalogs, and exits gracefully but for now its just...
	            System.out.println("Exiting program...");
	            System.exit(0); //...force exiting
	        } 
	        
	        else {
	            // Handle invalid input
	            System.out.println("Invalid command. Please try again.");
	        }
		    
	    }
	    scanner.close();
	

	}

	
	
	// Define methods to be called based on user input--------------------------------
	
	private static void executeAddEvent() {
	    TheEventManager.addEvent();
	}
	
	//!!!NEW METHODS HERE!!!

	
	// Display Menu------------------------------------------------
	
}




/*
 * TEMPLATE AND INSTRUCTIONS FOR USE
 * 
 * Instructions: 
 * - First, add your command to the enum (See commands enum for further details)
 * - Put the else if with your corresponding information in the while true loop
 * - Put your executeyourmethod method in the methods section
 * 
 * Templates for copy/paste
 * 
   else if (userInput.equalsIgnoreCase("yourcommand")) {
	            executeyourcommand();
	            break; // Exit the loop after executing the command
	        } 
	        
	        
	private static void executeyourcommand() {
	    
	    // Add your code, call the method you want to test
	}
	        */


