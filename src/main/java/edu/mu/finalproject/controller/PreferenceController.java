// Source(s): https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Date;

<<<<<<< HEAD
=======
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

>>>>>>> 475be702a3656ac66b37a9ccbfbc9b1e966d0f23
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
	 * 
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
	
<<<<<<< HEAD
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
=======
	public Playlist recommendByPreference(ArrayList<MediaProduct> catalog/*, Account user*/) {
		/*
		if (user == null) {
			System.out.println("We can't give you a recommendation. Please log in first!");
			return null;
		}
		*/
		if(userPreference != null) {
			String preferenceStr = userPreference.capitalizePreference();
			ArrayList<Song> filteredCatalog = new ArrayList<Song>();
			Playlist recommendedSongs = new Playlist(0, preferenceStr + " songs", preferenceStr, new Date(), false, filteredCatalog);
			for (MediaProduct obj : catalog) {
				if(obj.getClass() == Song.class) {
>>>>>>> 475be702a3656ac66b37a9ccbfbc9b1e966d0f23
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
