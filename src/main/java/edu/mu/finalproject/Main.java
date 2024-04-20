package edu.mu.finalproject;

import java.util.ArrayList;
import java.util.Date;

import edu.mu.finalproject.model.*;
import edu.mu.finalproject.controller.*;


public class Main {

	public static void main(String[] args) {
		
		// Unofficially create some songs
		Song song0 = new Song(0, "Gummy Bear", "gummy bear", new Date(), false, 3.2f, 0);
		Song song1 = new Song(1, "Peanut Butter Jelly Time", "peanut",new Date(), false, 2.1f, 0);
		Song song2 = new Song(2, "Peel the avocado", "avocado", new Date(), false, 8.9f, 0);
		Song song3 = new Song(3, "Hot potato", "potato", new Date(), false, 9f, 0);
		
		// Add songs to unorderedPlaylist
		Playlist unorderedPlaylist = new Playlist(0, "Songs that slap", null, new Date(), false, 0, new ArrayList<Song>());		
		unorderedPlaylist.getSongs().add(song0);
		unorderedPlaylist.getSongs().add(song1);
		unorderedPlaylist.getSongs().add(song2);
		unorderedPlaylist.getSongs().add(song3);
		
		// Add unorderedPlaylist & all individual songs to catalog
		ArrayList<MediaObject> catalog = new ArrayList<MediaObject>();
		catalog.add(unorderedPlaylist);
		for (Song song : unorderedPlaylist.getSongs()) {
			catalog.add(song);
		}
		
		

		/* UserInterface ui = new UserInterface();
		ui.start();
		*/

		// Download playlist & display playlist contents in html file
		DownloadPlaylistController downloadPlaylist = new DownloadPlaylistController();
		downloadPlaylist.downloadPlaylist(unorderedPlaylist);
		/*
		// Search the catalog
		SearchController searchController = new SearchController();
		searchController.search(catalog);	
		*/
		
		/* EventManager testManager = EventManager.getInstance(); //create an instance of EventManager
		testManager.addEvent();
		*/

		
	}
	


}