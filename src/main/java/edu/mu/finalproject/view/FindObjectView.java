package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.*;

/**
 * FindObjectView is a class that handles user input and formats it to be used as a query in searchMediaCatalog
 * Includes a method that gets information about the query object to be used in searching the catalog for an item
 * 
 * Helper class for FindObjectController 
 * @author etwil
 */
public class FindObjectView {
	
	/**
	 * Prompts user and gets information for a media product query.
	 * @param none
	 * @return String userInput representing the name of the object
	 * 
	 * @author etwil
	 */
	public static String getInformationFromUser() { 
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("What type do you want? ");
		
		
		
		String type;
		//Keeps prompting until proper input is entered
		while(true) {
			String userInput = scanner.nextLine().trim();

//Old code for when artist was in use
//			if (userInput.equalsIgnoreCase("artist")) {
//				
//				type = "artist";
//				break;
//			}
			if (userInput.equalsIgnoreCase("song")) {
				type = "song";
				break;
				
			}
			else if (userInput.equalsIgnoreCase("playlist")) {
				type = "playlist";
				break;
				
			}
			else {
				System.out.println("Type not recognized. Choose from: song, playlist");
				
			}
		}
		
		//Uses the name as a unique key to find objects
		String userInput;
		switch (type) {
//Old code for when artist was in use
//		case ("artist"):
//			System.out.println("What is the name of the artist?");
//			userInput = scanner.nextLine().trim();
//			return userInput;
			
		case ("song"):
			System.out.println("What is the name of the song?");
			userInput = scanner.nextLine().trim();
			
			return userInput;
			
		case ("playlist"):
			System.out.println("What is the name of the playlist?");
			userInput = scanner.nextLine().trim();
			return userInput;
		}
		
		return null;
		
	}

	

}



