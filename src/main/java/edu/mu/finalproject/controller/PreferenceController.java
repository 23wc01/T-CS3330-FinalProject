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
import edu.mu.finalproject.view.SetupPreferenceView;
import edu.mu.finalproject.view.RecommendByPreferenceView;

public class PreferenceController {
	private final static String jsonFilePath = "files/preferenceQuestions.json";
	private SetupPreferenceView setupPreferenceView;
	private static ArrayList<HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>>> json;
	private HashMap<Preference, Integer> scoreboard;
	
	public PreferenceController() {
		setupPreferenceView = new SetupPreferenceView();
	}
	
	private Boolean readJson() {
		Gson gson = new Gson();
		json = new ArrayList<HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>>>();
		try {
			json = new Gson().fromJson(new FileReader(jsonFilePath), json.getClass());
			return true;
		}
		catch (JsonSyntaxException e) {
			e.printStackTrace();
			return false;
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			return false;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void setupPreferences(/*Account user*/) {
		if (readJson()) {
			setupPreferenceView.displaySetupIntro();
			PreferenceQuestion preferenceQuestion;
			scoreboard = createScoreboard();
			
			for (Map questionAndChoices : json) {
				preferenceQuestion = new PreferenceQuestion();
				preferenceQuestion.setQuestion(questionAndChoices.get("question").toString());
				preferenceQuestion.setChoiceToPreferences((ArrayList<HashMap<String, String>>) questionAndChoices.get("choices"));
				setupPreferenceView.displayQuestion(preferenceQuestion.getQuestion());
				setupPreferenceView.displayChoices(preferenceQuestion.getChoices());
				int answer = setupPreferenceView.getInputAnswer();
				scoreQuestion(answer, preferenceQuestion);
			}
			Preference topPreference = getTopPreference();
			String preferenceStr = StringUtils.capitalize(topPreference.toString().toLowerCase());
			setupPreferenceView.displayPreference(preferenceStr);
		}
		else {
			System.out.println("Failed to read JSON file.");
		}
	}
	private HashMap<Preference, Integer> createScoreboard() {
		HashMap<Preference, Integer> scoreboard = new HashMap<Preference, Integer>();
		for(Preference preference : Preference.values()) {
			scoreboard.put(preference, 0);
		}
		return scoreboard;
	}
	private void scoreQuestion(int answer, PreferenceQuestion preferenceQuestion) {
		--answer; // Answers are 1-based. Decrement to get 0-based
		ArrayList<String> answerPreferences = preferenceQuestion.getAnswerPreferences();
		Preference answerPreference = Preference.valueOf(answerPreferences.get(answer).toUpperCase());
		recordScore(answerPreference);
	}
	
	private void recordScore(Preference answerPreference) {
		int newScore = scoreboard.get(answerPreference) + 1;
		scoreboard.put(answerPreference, newScore);
	}
	
	private Preference getTopPreference() {
		int maxScore = 0;
		Preference topPreference = null;
		for (Map.Entry<Preference, Integer> preference : scoreboard.entrySet()) {
			if(preference.getValue() > maxScore) {
				maxScore = preference.getValue();
				topPreference = preference.getKey();
			}
		}
		return topPreference;
	}
	
	public Playlist recommendByPreference(Preference preference, ArrayList<MediaObject> catalog) {
		if(preference != null) {
			String preferenceStr = StringUtils.capitalize(preference.toString().toLowerCase());
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
		
		else {
			setupPreferences();
			return recommendByPreference(preference, catalog);
		}
	}
}
