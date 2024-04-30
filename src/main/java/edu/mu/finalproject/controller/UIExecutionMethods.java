package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.view.EventView;
import edu.mu.finalproject.view.FavoritesView;

import java.util.Scanner;

import edu.mu.finalproject.*;

public class UIExecutionMethods {
	
	// Define methods to be called based on user input--------------------------------
	
		public static void executeAddEvent() {
		    EventView.viewAddEvent();
		}
		
		public static void executeDeleteEvent() {
			EventView.viewDeleteEvent();
		}
	
		public static void executeDisplayEvents() {
			EventView.getEventDisplayInfo();
		}
//WAITING FOR CATALOG:	
//		public static void executeDisplayFavorites() {
//			FavoritesView.getFavInfo(); 
//		} 
		
		public static void executeExit() {
			Scanner scanner = new Scanner(System.in);
			scanner.close();//Always close the stream from System.in
			System.exit(0);//Could add more sophisticated exiting here later
		}
// WAITING FOR CATALOG:
//		public static void executeFavorite() {
//			UserInterface.TheFavoritesManager.favorite(); 
//		}
		
		public static void executeSetupPreference() {
			Account user = new Account("23wc01", "secret"); //!!!!!!!! Store global user field in this class
			PreferenceController prefController = new PreferenceController();
			user.setUserPreference(prefController.newPreference());
		}
		
		public static void executeDownloadRecommendedPlaylist() {
			Account user = new Account("23wc01", "secret"); //!!!!!!!! Store global user field in this class

			DownloadPlaylistController downloadPlaylistController = new DownloadPlaylistController();
			if (downloadPlaylistController.downloadRecommendedPlaylist(user, catalog)) {	
				System.out.println("Download success");
			}
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
			//Call your function(s) here. 
		} 

*/