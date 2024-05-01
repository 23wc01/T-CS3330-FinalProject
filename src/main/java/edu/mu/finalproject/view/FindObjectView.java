package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.*;

public class FindObjectView {
	
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
		
		//Uses the name as a unique key to find objects
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

	

}



