package edu.mu.finalproject;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.*;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.EventView;
import edu.mu.finalproject.view.FavoritesView;

import java.util.Scanner;

public class UIExecutionMethods {
	
	// Define methods to be called based on user input--------------------------------
	
		public static void executeAddEvent() {
			Event myEvent = EventView.createEvent();
		    EventView.viewAddEvent(myEvent);
		}
		
		
		public static void executeDeleteEvent() {
			System.out.println("\nEnter information so we can find the event to delete\n ");
			Event eventToBeDeleted = EventView.createEvent(); 
			EventView.viewDeleteEvent(eventToBeDeleted);
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
//			MediaObject objectToBeFavorited = FindObjectController.searchMediaCatalog(null); //!!! Add real catalog here!
//			boolean result = FavoritesController.favorite(objectToBeFavorited);														
//		}
		
		public static void executeSetupPreference(Account user) {
			user = new Account("23wc01", "secret"); //!!!!!!!! Store global user field in this class
			PreferenceController preferenceController = new PreferenceController();
			user.setUserPreference(preferenceController.newPreference());
		}
		
		public static void executeDownloadRecommendedPlaylist(Account user) {
			user = new Account("23wc01", "secret"); //!!!!!!!! Store global user field in this class

			DownloadPlaylistController downloadPlaylistController = new DownloadPlaylistController();
			if (downloadPlaylistController.downloadRecommendedPlaylist(user, catalog)) {	
				System.out.println("Refresh folder if html file doesn't appear immidiately");
			}
		}
		public static void executeSearchCatalog() {
			SearchController searchController = new SearchController();
			searchController.search(catalog); // !!!!! GET CATALOG!
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