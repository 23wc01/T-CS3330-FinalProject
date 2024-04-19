package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.*;

public class UIExecutionMethods {
	
	// Define methods to be called based on user input--------------------------------
	
		public static void executeAddEvent() {
		    UserInterface.TheEventManager.addEvent();
		}
		
		public static void executeDisplayEvents() {
			UserInterface.TheEventManager.getEventDisplayInfo();
		}
		
		public static void executeExit() {
			System.exit(0);//Could add more sophisticated exiting here later
		}
		
		//!!!NEW METHODS HERE!!! try to put in alphabetical order

		
		// Display Menu------------------------------------------------
		public static void executeMenu() {
			System.out.println("Available commands:");
			for (ECommands command : ECommands.values()) {
			    System.out.println(command.getDescription());
			}
		}

}

/*Instructions for use: This file connects the input recieved to the methods you want to call
* Use the template to make a new entry
* 
* Template: 
	public static void executeMyFunctionality() {
			//Call your function here. 
		} 

*/