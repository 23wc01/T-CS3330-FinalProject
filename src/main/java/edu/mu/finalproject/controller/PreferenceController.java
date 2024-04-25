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
	private SetupPreferenceView setupPreferenceView;
	private Preference userPreference;
	
	private final static String jsonFilePath = "files/preferenceQuestions.json";
	private static ArrayList<HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>>> json;
	private HashMap<Preference, Integer> scoreboard;
	
	public PreferenceController(/* Account user*/) {
		setupPreferenceView = new SetupPreferenceView();
		// userPreference = user.getPreference();
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
	
	public boolean setupPreference(/*Account user*/) {
		/*if (user == null) {
			System.out.println("Must pass in a user account!");
			return false;
		}*/
		setupPreferenceView.displaySetupIntro();
		int answer = setupPreferenceView.getSetupAnswer();
		boolean setupSuccess = false;
		if (answer == 0) {
			setupSuccess = preferenceQuiz();
			if (!setupSuccess) {
				setupPreferenceView.displayQuizError();
			}
		}
		else if (answer == 1 || !setupSuccess) {
			choosePreference();
			setupSuccess = true;
		}
		else {
			System.out.println("Failed to setup user's preference.");
			setupSuccess = false;
		}
		return setupSuccess;
	}
	
	public void choosePreference() {
		setupPreferenceView.displayQuestion("What is your listening preference?");
		ArrayList<String> choices = new ArrayList<String>();
		for (Preference preference : Preference.values()) {
			choices.add(preference.capitalizePreference());
		}
		setupPreferenceView.displayChoices(choices);
		int preferenceAnswer = setupPreferenceView.getInputAnswer(choices.size());
		--preferenceAnswer; // Answers are 1-based on UI, but need to get 0-based for ArrayList indexing
		String preferenceStr = choices.get(preferenceAnswer);
		userPreference = Preference.toPreference(preferenceStr);
		setupPreferenceView.displayPreference(preferenceStr);
		// user.setPreference(userPreference);
	}
	
	public boolean preferenceQuiz() {
		if (readJson()) {
			setupPreferenceView.displayQuizIntro();
			scoreboard = createScoreboard();
			askQuestions();
			userPreference = getTopPreference();
			setupPreferenceView.displayPreference(userPreference.capitalizePreference());
			// user.setPreference(userPreference);
			return true;
		}
		else {
			System.out.println("Failed to read JSON file.");
			return false;
		}
	}
	private HashMap<Preference, Integer> createScoreboard() {
		HashMap<Preference, Integer> scoreboard = new HashMap<Preference, Integer>();
		for(Preference preference : Preference.values()) {
			scoreboard.put(preference, 0);
		}
		return scoreboard;
	}
	
	private void askQuestions() {
		PreferenceQuestion preferenceQuestion;
		for (Map questionAndChoices : json) {
			preferenceQuestion = new PreferenceQuestion();
			preferenceQuestion.setQuestion(questionAndChoices.get("question").toString());
			preferenceQuestion.setChoiceToPreferences((ArrayList<HashMap<String, String>>) questionAndChoices.get("choices"));
			setupPreferenceView.displayQuestion(preferenceQuestion.getQuestion());
			setupPreferenceView.displayChoices(preferenceQuestion.getChoices());
			int answer = setupPreferenceView.getInputAnswer(preferenceQuestion.getChoices().size());
			scoreQuestion(answer, preferenceQuestion);
		}
	}
	
	private void scoreQuestion(int answer, PreferenceQuestion preferenceQuestion) {
		--answer; // Answers are 1-based in UI. Decrement to get 0-based
		ArrayList<String> answerPreferences = preferenceQuestion.getAnswerPreferences();
		Preference answerPreference = Preference.toPreference(answerPreferences.get(answer));
		recordScore(answerPreference);
	}
	
	private void recordScore(Preference answerPreference) {
		int newScore = scoreboard.get(answerPreference);
		++newScore;
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
	
	public Playlist recommendByPreference(ArrayList<MediaObject> catalog) {
		if(userPreference != null) {
			String preferenceStr = userPreference.capitalizePreference();
			ArrayList<Song> filteredCatalog = new ArrayList<Song>();
			Playlist recommendedSongs = new Playlist(0, preferenceStr + " songs", preferenceStr, new Date(), false, -1f, filteredCatalog);
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
			setupPreference();
			return recommendByPreference(catalog);
		}
	}
}
