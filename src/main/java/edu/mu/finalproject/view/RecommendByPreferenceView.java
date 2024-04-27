package edu.mu.finalproject.view;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Song;

public class RecommendByPreferenceView {
	public Boolean displaySongRecommendations(String preference, ArrayList<Song> recommendedSongs) {
		if (preference == null || recommendedSongs == null) {
			System.out.println("Sorry, no songs of your preference type added to catalog yet.");
			return false;
		}
		System.out.println("\nRecommended for " + preference + " lovers...");
		System.out.println("-------------------------------");
		if(recommendedSongs != null) {
			for (Song song : recommendedSongs) {
				System.out.println(song.toString());
			}
			System.out.println("-------------------------------\n");
		}
		return true;
		
	}
}
