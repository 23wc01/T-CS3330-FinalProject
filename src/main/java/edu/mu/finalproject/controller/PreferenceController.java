package edu.mu.finalproject.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.PreferenceQuestion;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.SetupPreferenceView;
import edu.mu.finalproject.view.RecommendByPreferenceView;

public class PreferenceController {
	SetupPreferenceView setupPreferenceView;
	public PreferenceController() {
		setupPreferenceView = new SetupPreferenceView();
	}
	public void setupPreferences() {
		String question = "How would you describe your tastes?";
		
		HashMap<String, Preference> choicesAndScores = new HashMap<String, Preference>();
		choicesAndScores.put("Old-fashion & wild", Preference.COUNTRY);
		choicesAndScores.put("Modern & trendy", Preference.POP);
		choicesAndScores.put("", Preference.RAP);
		choicesAndScores.put("Noncomformist & raw", Preference.ROCK);
		choicesAndScores.put("Intellectual & expensive", Preference.SOUL);
		
		PreferenceQuestion preferenceMCQuestion = new PreferenceQuestion(question, choicesAndScores);
		
		// Need to have Account user as argument in setupPreferences
		setupPreferenceView.displayQuestion("Hello?");
		String[] choices = {"a", "b", "c"};
		setupPreferenceView.displayChoices(choices);
		System.out.println(setupPreferenceView.getInputAnswer());
	}
	public Playlist recommendByPreference(Preference preference, ArrayList<MediaObject> catalog) {
		String preferenceStr = preference.toString().toLowerCase();
		ArrayList<Song> filteredCatalog = new ArrayList<Song>();
		Playlist recommendedSongs = new Playlist(0, preferenceStr + " songs", preferenceStr, new Date(), false, -1f, filteredCatalog);
		for (MediaObject obj : catalog) {
			if(obj.getClass() == Song.class) {
				Song song = (Song) obj;
				if(song.getPreference() == preference) {
					recommendedSongs.getSongs().add(song);
				}
			}
		}
		RecommendByPreferenceView recommendedView = new RecommendByPreferenceView();
		recommendedView.displaySongRecommendations(preferenceStr, recommendedSongs.getSongs());
		return recommendedSongs;
	}
}
