// Source(s): https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
package edu.mu.finalproject.controller;

import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.PreferenceQuestion;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;
import edu.mu.finalproject.view.SetupPreferenceView;
import edu.mu.finalproject.view.RecommendByPreferenceView;

public class PreferenceController {
	private SetupPreferenceView setupPreferenceView;
	private Preference userPreference;

	
	public PreferenceController(/* Account user*/) {
		this.setupPreferenceView = new SetupPreferenceView();
		// this.userPreference = user.getPreference();
	}
	
	public boolean modifyPreference(/*Account user*/) {
		/*if (user == null) {
			System.out.println("Must pass in a user account!");
			return false;
		}*/
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
				return false;
		}
		
		userPreference = context.updatePreference(setupPreferenceView);
		
		if (userPreference == null) {
			setupPreferenceView.displayQuizError();
			return false;
		}
		return true;
	}
	
	public Playlist recommendByPreference(ArrayList<MediaObject> catalog/*, Account user*/) {
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
			for (MediaObject obj : catalog) {
				if(obj.getClass() == Song.class) {
					Song song = (Song) obj;
					if(song.getPreference() == userPreference) {
						recommendedSongs.getSongs().add(song);
					}
				}
			}
			RecommendByPreferenceView recommendedView = new RecommendByPreferenceView();
			recommendedView.displaySongRecommendations(preferenceStr, recommendedSongs.getSongs());
			return recommendedSongs;
		}
		
		else {
			modifyPreference();
			return recommendByPreference(catalog);
		}
	}
}
