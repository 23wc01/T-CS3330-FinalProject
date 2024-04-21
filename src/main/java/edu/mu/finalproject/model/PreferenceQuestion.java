package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.HashMap;

public class PreferenceQuestion {
	private String question;
	private HashMap<String, Preference> choicesToPreferences = new HashMap<String, Preference>();
	
	public PreferenceQuestion(String question, HashMap<String, Preference> choicesToPreferences) {
		super();
		this.question = question;
		this.choicesToPreferences = choicesToPreferences;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public HashMap<String, Preference> getChoiceToScoring() {
		return choicesToPreferences;
	}
	public void setChoiceToScoring(HashMap<String, Preference> choicesToPreferences) {
		this.choicesToPreferences = choicesToPreferences;
	}
}
