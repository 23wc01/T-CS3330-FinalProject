package edu.mu.finalproject;

import java.util.ArrayList;
import java.util.Date;

import edu.mu.finalproject.model.*;
import edu.mu.finalproject.controller.*;


public class Main {

	public static void main(String[] args) {
		// Create some songs
		Song song0 = new Song(0, "Gummy Bear", "Pop song", new Date(), false, 0, 0);
		Song song1 = new Song(1, "Peanut Butter Jelly Time", "Pop song",new Date(), false, 0, 0);
		Song song2 = new Song(2, "Peel the avocado", "R&B song", new Date(), false, 0, 0);
		Song song3 = new Song(3, "Hot potato", "Pop song", new Date(), false, 0, 0);
		
		// Add songs to unorderedPlaylist
		Playlist unorderedPlaylist = new Playlist(0, "Unordered pee songs", null, new Date(), false, 0, new ArrayList<Song>());		
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
		SearchController searchController = new SearchController();
		searchController.searchSort("Pee", catalog);	
		
		
		EventManager testManager = EventManager.getInstance(); //create an instance of EventManager
		testManager.addEvent();
		
		
	}
	


}
