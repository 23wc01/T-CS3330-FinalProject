package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreferenceQuestion {
	private String question;
	private ArrayList<HashMap<String, String>> choicesToPrefs;
	private ArrayList<String> answerPreferences = new ArrayList<String>();

	public String getQuestion() {
		return question;
	}
	public Boolean setQuestion(String question) {
		if(question == null) {
			return false;
		}
		this.question = question;
		return true;
	}

	public ArrayList<HashMap<String, String>> getChoiceToPreferences() {
		return choicesToPrefs;
	}
	public Boolean setChoiceToPreferences(ArrayList<HashMap<String, String>> choicesToPrefs) {
		if(choicesToPrefs == null) {
				return false;
		}
		this.choicesToPrefs = choicesToPrefs;
		return true;
	}
	public ArrayList<String> getChoices() {
		ArrayList<String> choices = new ArrayList<String>();
		for(Map<String, String> choiceToPreferences : choicesToPrefs) {
			choices.add(choiceToPreferences.get("choice").toString());
		}
		return choices;
	}
	public ArrayList<String> getAnswerPreferences() {
		for(Map<String, String> choiceToPreferences : choicesToPrefs) {
			answerPreferences.add(choiceToPreferences.get("preference").toString());
		}
		return answerPreferences;
	}

 }
