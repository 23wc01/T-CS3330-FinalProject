package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PreferenceQuestion {
	private String question;
	private ArrayList<HashMap<String, String>> choicesToPrefs;
	public PreferenceQuestion() {}
	public PreferenceQuestion(String question, ArrayList<HashMap<String, String>> choicesToPrefs) {
		super();
		this.question = question;
		this.choicesToPrefs = choicesToPrefs;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<HashMap<String, String>> getChoiceToPreferences() {
		return choicesToPrefs;
	}
	public void setChoiceToPreferences(ArrayList<HashMap<String, String>> choicesToPrefs) {
		this.choicesToPrefs = choicesToPrefs;
	}
	public ArrayList<String> getChoices() {
		ArrayList<String> choices = new ArrayList<String>();
		for(Map<String, String> choiceToPreferences : choicesToPrefs) {
			choices.add(choiceToPreferences.get("choice").toString());
		}
		return choices;
	}
	public ArrayList<String> getAnswerPreferences() {
		ArrayList<String> answerPreferences = new ArrayList<String>();
		for(Map<String, String> choiceToPreferences : choicesToPrefs) {
			answerPreferences.add(choiceToPreferences.get("preference").toString());
		}
		return answerPreferences;
	}
 }
