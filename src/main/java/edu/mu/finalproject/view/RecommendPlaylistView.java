package edu.mu.finalproject.view;

import java.util.ArrayList;

import edu.mu.finalproject.model.Song;

public class RecommendPlaylistView {
	public Boolean displaySongRecommendations(String preference, ArrayList<Song> recommendedSongs) {
		if (preference == null || recommendedSongs == null || recommendedSongs.size() == 0) {
			System.out.println("Sorry, no songs of your preference type added to catalog yet.");
			return false;
		}
		System.out.println("\nRecommended for " + preference + " lovers...");
		System.out.println("-------------------------------");
		for (Song song : recommendedSongs) {
			System.out.println(song.toString());
		}
		System.out.println("-------------------------------\n");
		System.out.println("If you haven't already, try 'download_recommended_playlist'!\n");
		
		return true;
		
	}
}
