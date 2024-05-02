package edu.mu.finalproject;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.*;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.EventView;
import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;

import java.util.Scanner;
/**
 * The UIExecutionMethods class manages flow from the switch statement in UserInterface.
 * The execution methods handle set up and initiation of each of the core features.
 * 
 * This class is not meant to be instantiated
 * It is part of the Mediator Design Pattern.
 */
public class UIExecutionMethods {
	//Instances here
	static EventView eventview = new EventView();
	static FavoritesView favoritesview = new FavoritesView();
	static FindObjectController findobjectcontroller = new FindObjectController();
	
	//Define methods to be called based on user input--------------------------------
	 
	
		public static void executeAddEvent() {
			Event myEvent = eventview.createEvent();
		    EventView.viewAddEvent(myEvent);
		}
		
		  
		public static void executeDeleteEvent() {
			System.out.println("\nEnter information so we can find the event to delete\n ");
			Event eventToBeDeleted = eventview.createEvent(); 
			EventView.viewDeleteEvent(eventToBeDeleted);
		}
		
		public static void executeDisplayEvents() {
			eventview.getEventDisplayInfo(EventSingleton.getInstance().getEventCollection());
		}
//WAITING FOR CATALOG:	
		public static void executeDisplayFavorites() {
			favoritesview.getFavInfo(null); //!!!!ADD REAL CATALOG HERE
		} 
		
		public static void executeExit() {
			Scanner scanner = new Scanner(System.in);
			scanner.close();//Always close the stream from System.in
			System.exit(0);//Could add more sophisticated exiting here later
		}
 //WAITING FOR CATALOG:
		public static void executeFavorite() {
		    String userQuery = FindObjectView.getInformationFromUser();
		
			MediaProduct objectToBeFavorited = findobjectcontroller.searchMediaCatalog(null, userQuery); //!!! Add real catalog here!
			favoritesview.displayFavoriteResult(objectToBeFavorited);														
		}
		
		public static void executeSetupPreference(Account user) {
			user = new Account(0, "23wc01", "secret"); //!!!!!!!! Store global user field in this class
			SetupPreferenceController setupPreferenceController = new SetupPreferenceController();
			user.setUserPreference(setupPreferenceController.newPreference());
		}
		
		public static void executeDownloadRecommendedPlaylist(Account user) {
			user = new Account(0, "23wc01", "secret"); //!!!!!!!! Store global user field in this class

			DownloadPlaylistController downloadPlaylistController = new DownloadPlaylistController();
			if (downloadPlaylistController.downloadRecommendedPlaylist(user, CatalogSingleton)) {	
				System.out.println("Refresh folder if html file doesn't appear immidiately");
			}
		}
		public static void executeSearchCatalog() {
			SearchController searchController = new SearchController();
			searchController.search(CatalogSingleton); // !!!!! GET CATALOG!
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