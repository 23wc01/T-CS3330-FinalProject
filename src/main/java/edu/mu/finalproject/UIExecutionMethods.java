package edu.mu.finalproject;

import edu.mu.finalproject.controller.DownloadPlaylistController;
import edu.mu.finalproject.controller.FindObjectController;
import edu.mu.finalproject.controller.MediaCatalogController;
import edu.mu.finalproject.controller.RecommendPlaylistController;
import edu.mu.finalproject.controller.SearchController;
import edu.mu.finalproject.controller.SetupPreferenceController;
import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.ECommands;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.view.EventView;
import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;
import recommendPlaylistTests.*;

import java.util.ArrayList;
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
	private static EventView eventview = new EventView();
	private static FavoritesView favoritesview = new FavoritesView();
	private static FindObjectController findobjectcontroller = new FindObjectController();
	private static MediaCatalogController mediaCatalogController = new MediaCatalogController();

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
	
		public static void executeDisplayFavorites() {
			favoritesview.getFavInfo(CatalogSingleton.getInstance().getMediaProductCollection()); //!!!!ADD REAL CATALOG HERE
		} 
	
 
		// New method to test catalog functionality
    	public static void executeTestCatalogFunctionality() {
        	System.out.println("Starting catalog functionality tests...");
        	

        	// Display all media in the catalog
        	System.out.println("Displaying all media:");
        	mediaCatalogController.displayAll();

        	// Search for a specific song
	        String searchQuery = "God's Menu"; // Example song name, change as needed
        	System.out.println("Searching for song: " + searchQuery);
        	mediaCatalogController.findAndDisplaySongs(searchQuery);

        	// Add a new playlist 
        	Playlist newPlaylist = new Playlist(1, "Test Playlist", "Description", new Date(), false, new ArrayList<>());
        	System.out.println("Adding a new playlist...");
        	mediaCatalogController.addMedia(newPlaylist);

		Playlist newPlaylist = new Playlist(2, "Test Playlist", "Description", new Date(), false, new ArrayList<>());
        	System.out.println("Adding a new playlist...");
        	mediaCatalogController.addMedia(newPlaylist);

		// Check if there are stil media items in Catalog, if not add to display
		if (!CatalogSingleton.getInstance().getMediaProductCollection().isEmpty()) {
           		System.out.println("Catalog still contains media items!");
            		mediaCatalogController.displayAll();
        	} else {
            		System.out.println("Catalog is empty after deletion.");
        	}

        	// Display all media after adding new playlist
        	System.out.println("Displaying all media after adding a new playlist:");
        	mediaCatalogController.displayAll();

        	// Delete a media item 
	        int mediaIdToDelete = 2; 
	        System.out.println("Deleting media with ID: " + mediaIdToDelete);
	        mediaCatalogController.deleteMedia(mediaIdToDelete);

		
	        // Display all media after deletion
	        System.out.println("Displaying all media after deletion:");
	        mediaCatalogController.displayAll();
	
	        // Shuffle and display the catalog to ensure shuffle functionality
		// Not sure about this one. I will ask Prof about this more. Playing by string or better randint id
	        System.out.println("Displaying shuffled media:");
	        mediaCatalogController.displayShuffle();
		}

 
		public static void executeFavorite() {
		    String userQuery = FindObjectView.getInformationFromUser();
		
			MediaProduct objectToBeFavorited = findobjectcontroller.searchMediaCatalog(CatalogSingleton.getInstance().getMediaProductCollection(), userQuery); //!!! Add real catalog here!
			favoritesview.displayFavoriteResult(objectToBeFavorited);														
		}
		
		public static void executeSearchCatalog() {
			SearchController searchController = new SearchController();
			searchController.search();
		}

		public static void executeSetupPreference() {
			UserInterface.setUser(new Account(0, "test", "test")); //!!!!!!!! Store global user field in this class
			if (UserInterface.getAccount() == null) {
				System.err.println("Must 'login' first before setting preference!");
			}
			SetupPreferenceController setupPreferenceController = new SetupPreferenceController();
			setupPreferenceController.newPreference(UserInterface.getAccount());
		}
		
		public static void executeRecommendPlaylist() {			
			if (UserInterface.getAccount() == null || CatalogSingleton.getInstance() == null) {
				System.err.println("'login' first or check CatalogSingleton instance is not null!");
			}
			RecommendPlaylistController recommendPlaylistController = new RecommendPlaylistController();
			recommendPlaylistController.recommendPlaylist(UserInterface.getAccount().getUserPreference(), CatalogSingleton.getInstance().getMediaProductCollection());
		}
		
		public static void executeDownloadRecommendedPlaylist() {
			DownloadPlaylistController downloadPlaylistController = new DownloadPlaylistController();
			downloadPlaylistController.downloadRecommendedPlaylist(UserInterface.getAccount(), CatalogSingleton.getInstance().getMediaProductCollection());
		}
		
		//!!!NEW METHODS HERE!!! try to put in alphabetical order
		
		
		// Display Menu------------------------------------------------
		public static void executeMenu() {
			System.out.println("Available commands:");
			for (ECommands command : ECommands.values()) {
			    System.out.println(command.getDescription());
			}
		}
		
		//Exit---------------------------------------------------------
		public static void executeExit() {
			Scanner scanner = new Scanner(System.in);
			scanner.close();//Always close the stream from System.in
			System.exit(0);//Could add more sophisticated exiting here later
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
