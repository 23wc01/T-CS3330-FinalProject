package edu.mu.finalproject;

import java.util.ArrayList;
import java.util.Date;

import edu.mu.finalproject.controller.*;
import edu.mu.finalproject.model.*;



public class Main {

	public static void main(String[] args) {
		
		// Unofficially create some songs
		Song song0 = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(), false, "icanrockyourworld", Preference.EDM);
		Song song1 = new Song(1, "Peanut Butter Jelly Time", "peanut",new Date(), false, "Kids Tunez", Preference.RAP);
		Song song2 = new Song(2, "The Guacamole Song", "avocado", new Date(), false, "Dr. Jean", Preference.POP);
		Song song3 = new Song(3, "Hot potato", "potato", new Date(), false, "Patty Shukla", Preference.POP);
		
		// Add songs to unorderedPlaylist
		Playlist unorderedPlaylist = new Playlist(0, "Songs that slap", null, new Date(), false, new ArrayList<Song>());		
		unorderedPlaylist.getSongs().add(song0);
		unorderedPlaylist.getSongs().add(song1);
		unorderedPlaylist.getSongs().add(song2);
		unorderedPlaylist.getSongs().add(song3);
		
		// Add unorderedPlaylist & all individual songs to catalog
		ArrayList<MediaProduct> catalog = new ArrayList<MediaProduct>();
		catalog.add(unorderedPlaylist);
		for (Song song : unorderedPlaylist.getSongs()) {
			catalog.add(song);
		}
		
		
		UserInterface ui = new UserInterface();
		ui.start();			
		
		
		
		/*// Unofficially create new user
		Account user = new Account("blobby", "secret"); //!!!!!!!! Store global user field in this class
		
		// Setup user's preference in 1 of 2 ways
		PreferenceController prefController = new PreferenceController();
		user.setUserPreference(prefController.newPreference());

		// Download recommended playlist
		DownloadPlaylistController downloadPlaylistController = new DownloadPlaylistController();
		if (downloadPlaylistController.downloadRecommendedPlaylist(user, catalog)) {	
			System.out.println("*Refresh folder if html file doesn't appear immidiately*\n\n");
		}

		// Search for anything 
		SearchController searchController = new SearchController();
		searchController.search(catalog);	
		
		*/
	}
	


}