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
	/**
	 * Setup preference by scoring quiz answers
	 * @return Preference 
	 */
	public Preference setupPreference(SetupPreferenceView view) {
		setView(view);
		if (getView() == null) {
			System.out.println("View not visible");
			return null;
		}
		if (readJson()) {
			getView().displayQuizIntro();
			scoreboard = createScoreboard();
			askQuestions();
			userPreference = getTopPreference();
			getView().displayPreference(userPreference.capitalizePreference());
			return userPreference;
		}
		else {
			System.out.println("Failed to read JSON file.");
			return null;
		}
	}
	
	/**
	 * Reads from in questions json files in files folder
	 * @return false if read failed, else true
	 */
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

	/**
	 * Creates a "scoreboard" which is a HashMap containing all enum values in Preference mapped to initial score of 0.
	 * @return initialized HashMap<Preference, Integer>
	 */
	private HashMap<Preference, Integer> createScoreboard() {
		HashMap<Preference, Integer> scoreboard = new HashMap<Preference, Integer>();
		for(Preference preference : Preference.values()) {
			scoreboard.put(preference, 0);
		}
		return scoreboard;
	}
	
	/**
	 * Creates PreferenceQuestions for each question in json. 
	 * Specifies to view how to appropriately print the different members of PreferenceQuestion
	 * Calls scoreQuestion() to score the user's answer
	 * @return false if error encountered for question scoring
	 */
	private Boolean askQuestions() {
		ArrayList<Boolean> successes = new ArrayList<Boolean>();
		PreferenceQuestion preferenceQuestion;
		for (Map questionAndChoices : json) {
			preferenceQuestion = new PreferenceQuestion();
			preferenceQuestion.setQuestion(questionAndChoices.get("question").toString());
			preferenceQuestion.setChoiceToPreferences((ArrayList<HashMap<String, String>>) questionAndChoices.get("choices"));
			getView().displayQuestion(preferenceQuestion.getQuestion());
			getView().displayChoices(preferenceQuestion.getChoices());
			int answer = view.getInputAnswer(preferenceQuestion.getChoices().size());
			successes.add(scoreQuestion(answer, preferenceQuestion));
		}
		if (successes.contains(false)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Finds the preference that maps to user's input answer. Calls recordScore() to score it
	 * @param answer
	 * @param preferenceQuestion
	 * @return false if @param answer from user less than 1/ @param preferenceQuestion is null
	 */
	public Boolean scoreQuestion(int answer, PreferenceQuestion preferenceQuestion) {
		if (answer < 1 || preferenceQuestion == null) {
			return false;
		}
		--answer; // Answers are 1-based in UI. Decrement to get 0-based
		ArrayList<String> answerPreferences = preferenceQuestion.getAnswerPreferences();
		Preference answerPreference = Preference.toPreference(answerPreferences.get(answer));
		return recordScore(answerPreference);
	}
	
	/**
	 * Increment score of @param answerPreference
	 * @param answerPreference
	 * @return false if answerPreference = null, else true
	 */
	public Boolean recordScore(Preference answerPreference) {
		if (answerPreference == null) {
			return false;
		}
		int newScore = scoreboard.get(answerPreference);
		++newScore;
		scoreboard.put(answerPreference, newScore);
		return true;
	}
	
	/**
	 * Compare scores in scoreboard. 
	 * @return Preference mapped to score with the max value will be returned
	 */
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
	
// Setters & Getters
	private Boolean setView(SetupPreferenceView view) {
		if(view == null) {
			return false;
		}
		this.view = view;
		return true;
	}
	private SetupPreferenceView getView() {
		return view;
	}
}
