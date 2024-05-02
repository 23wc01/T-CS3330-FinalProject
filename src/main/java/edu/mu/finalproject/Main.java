package edu.mu.finalproject;

import edu.mu.finalproject.controller.MediaCatalogController;

public class Main {

	public static void main(String[] args) {
		// Initialize the media catalog controller
		MediaCatalogController controller = new MediaCatalogController();

		// Load media from files
		controller.loadMediaFromFiles();

		// Display all media in the catalog
		System.out.println("Displaying all media in the catalog:");
		controller.displayAll();

		// Test search functionality
		String searchQuery = "God's Menu";
		System.out.println("Searching for '" + searchQuery + "'...");
		controller.findAndDisplaySongs(searchQuery);

		// Test deletion of a specific media (e.g., by ID)
		int mediaIdToDelete = 1; // Replace with the ID of the media you want to delete
		System.out.println("Deleting media with ID: " + mediaIdToDelete);
		controller.deleteMedia(mediaIdToDelete);

		// Display all media after deletion
		System.out.println("Displaying all media after deletion:");
		controller.displayAll();

		// Test shuffle functionality
		System.out.println("Displaying shuffled media:");
		controller.displayShuffle();
	}
}
