package edu.mu.finalproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.SearchComparator;

public class Main {

	public static void main(String[] args) {
		Song song0 = new Song(0, "Gummy Bear", "Pop song", new Date(), false, 0, 0);
		Song song1 = new Song(1, "Peanut Butter Jelly Time", "Pop song",new Date(), false, 0, 0);
		Song song2 = new Song(2, "Peel the avocado", "R&B song", new Date(), false, 0, 0);
		Song song3 = new Song(3, "Hot potato", "Pop song", new Date(), false, 0, 0);
		ArrayList<Song> songsList = new ArrayList<Song>();
		songsList.add(song0);
		songsList.add(song1);
		songsList.add(song2);
		songsList.add(song3);
		
		System.out.println("Unordered:");
		for (Song song : songsList) {
			System.out.println("'" + song.getName() + "'\t" + song.toString().toString());
		}
		

		SearchComparator songSearch = new SearchComparator("Gu");
		Collections.sort(songsList, songSearch);
		Playlist yourPlaylist = new Playlist(0, "Random songs", null, new Date(), false, 0, songsList);

		System.out.println("\n\nOrdered:");
		for (Song song : songsList) {
			System.out.println(song);
		}
		Playlist myPlaylist = new Playlist(0, "Matched songs", null, new Date(), false, 0, songsList);
		Playlist addedPlaylist = new Playlist(0, "Added songs", null, new Date(), false, 0, songsList);
		
		ArrayList<MediaObject> allPlaylists = new ArrayList<MediaObject>();
		allPlaylists.add(myPlaylist);
		allPlaylists.add(yourPlaylist);
		allPlaylists.add(addedPlaylist);
		allPlaylists.add(song0);
		
		SearchComparator search1 = new SearchComparator("Gu");
		Collections.sort(allPlaylists, search1);
		
		for (MediaObject mediaObj : allPlaylists) {
			System.out.println(mediaObj);
		}
		System.out.println("New search:" + "Peel");
		
		SearchComparator search2 = new SearchComparator("Peel");
		Collections.sort(allPlaylists, search2);
		
		for (MediaObject mediaObj : allPlaylists) {
			System.out.println(mediaObj);
		}
	}

}
