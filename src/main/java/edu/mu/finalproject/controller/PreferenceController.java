// Source(s): https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Date;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;
import edu.mu.finalproject.view.SetupPreferenceView;
import edu.mu.finalproject.view.RecommendByPreferenceView;

public class PreferenceController {
	private SetupPreferenceView setupPreferenceView;
	private Preference userPreference;

	
	public PreferenceController() {
		this.setupPreferenceView = new SetupPreferenceView();	
	}
	/**
	 * Setup user listening preference in 1 of 2 strategies
	 * @return
	 */
	public Preference newPreference() {
		SetupPreferenceContext context;

		setupPreferenceView.displaySetupIntro();
		int answer = setupPreferenceView.getSetupAnswer();
		switch(answer) {
			case(0):
				context = new SetupPreferenceContext(new QuizSetupPreferenceStrategy());
				break;
			case(1):
				context = new SetupPreferenceContext(new ManualSetupPreferenceStrategy());
				break;
			default:
				return null;
		}
		
		userPreference = context.updatePreference(setupPreferenceView);
		
		if (userPreference == null) {
			setupPreferenceView.displayQuizError();
		}
		return userPreference;
	}
	
	/**
	 * Create a playlist storing only songs in @param catalog that match @param preference
	 * @param catalog
	 * @param preference
	 * @return
	 */
	public Playlist recommendPlaylist(Preference preference, ArrayList<MediaProduct> catalog) {
		if(preference != null) {
			String preferenceStr = preference.capitalizePreference();
			ArrayList<Song> filteredCatalog = new ArrayList<Song>();
			Playlist recommendedSongs = new Playlist(0, preferenceStr + " songs", preferenceStr, new Date(), false, filteredCatalog);
			for (MediaProduct obj : catalog) {
				if (obj instanceof Song) {
					Song song = (Song) obj;
					if(song.getPreference() == preference) {
						recommendedSongs.getSongs().add(song);
					}
				}
			}
			RecommendByPreferenceView recommendedView = new RecommendByPreferenceView();
			recommendedView.displaySongRecommendations(preferenceStr, recommendedSongs.getSongs());
			System.out.println(recommendedSongs.getName());
			return recommendedSongs;
		}
		
		else {
			System.err.println("Parameter(s) passed into recommendPlaylist() are null. Check that you've setup a preference");
			return null;
		}
	}
}
