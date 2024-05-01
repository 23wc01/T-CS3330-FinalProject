package edu.mu.finalproject.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.PreferenceQuestion;
import edu.mu.finalproject.view.SetupPreferenceView;

public class QuizSetupPreferenceStrategy implements ISetupPreferenceStrategy {
	private SetupPreferenceView view;
	private Preference userPreference;
	
	private final static String jsonFilePath = "files/preferenceQuestions.json";
	private static ArrayList<HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>>> json;
	private HashMap<Preference, Integer> scoreboard;
	
	@Override
	public Preference setupPreference(SetupPreferenceView view) {
		setView(view);
		if (view == null) {
			System.out.println("View not visible");
			return null;
		}
		if (readJson()) {
			view.displayQuizIntro();
			scoreboard = createScoreboard();
			askQuestions();
			userPreference = getTopPreference();
			view.displayPreference(userPreference.capitalizePreference());
			// user.setPreference(userPreference);
			return userPreference;
		}
		else {
			System.out.println("Failed to read JSON file.");
			return null;
		}
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
			view.displayQuestion(preferenceQuestion.getQuestion());
			view.displayChoices(preferenceQuestion.getChoices());
			int answer = view.getInputAnswer(preferenceQuestion.getChoices().size());
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
	
	private void setView(SetupPreferenceView view) {
		this.view = view;
	}
	
}
