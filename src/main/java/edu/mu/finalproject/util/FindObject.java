package edu.mu.finalproject.util;

import java.util.Scanner;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.*;

public class FindObject {
	
	public static String getInformationFromUser() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("What type do you want? ");
		
		
		
		String type;
		//Keeps prompting until proper input is entered
		while(true) {
			String userInput = scanner.nextLine().trim();
			
			if (userInput.equalsIgnoreCase("artist")) {
				
				type = "artist";
				break;
			}
			else if (userInput.equalsIgnoreCase("song")) {
				type = "song";
				break;
				
			}
			else if (userInput.equalsIgnoreCase("playlist")) {
				type = "playlist";
				break;
				
			}
			else {
				System.out.println("Type not recognized. Choose from: artist, song, playlist");
				
			}
		}
		
		//Uses the name as a unique key to find objects. Can modify later
		String userInput;
		switch (type) {
		case ("artist"):
			System.out.println("What is the name of the artist?");
			userInput = scanner.nextLine().trim();
			return userInput;
			
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
	
	public static MediaObject searchMediaCatalog(){
		String userQuery = getInformationFromUser();
		
		if(userQuery == null) {
			System.out.println("Failed to retieve information.");
		}
		
//		for(MediaObject traverseObject : catalog) { //replace with real catalog when available
//			if (traverseObject.getName().equalsIgnoreCase(userQuery)) {
//				return traverseObject;
//			}
//		}
			return null;
	}
	

}


/*System.out.println("What is the name of the artist?");
			String userInput2 = scanner.nextLine().trim();
			
			userInput = userInput1 + ", " + userInput2;
			*/
