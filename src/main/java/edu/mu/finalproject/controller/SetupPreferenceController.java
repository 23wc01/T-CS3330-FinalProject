// Source(s): https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;
import edu.mu.finalproject.view.SetupPreferenceView;

public class SetupPreferenceController {
	private SetupPreferenceView setupPreferenceView;

	private Preference userPreference;

	/**
	 * Constructor auto-initializes setupPreferenceView
	 */
	public SetupPreferenceController() {
		this.setupPreferenceView = new SetupPreferenceView();	
	}
	
	/**
	 * Change account's listening preference using 1 of 2 strategies 
	 * @param account
	 * @return Preference based on set using which ever strategy selected. Returns null if account = null
	 */
	public Boolean newPreference(Account account) {
		if (account == null) {
			setupPreferenceView.displayQuizError("Please 'login' first");
			return false;
		}
		setupPreferenceView.displaySetupIntro();
		int answer = setupPreferenceView.getSetupAnswer();
		SetupPreferenceContext context;
		context = setContext(answer);
		
		userPreference = context.updatePreference(setupPreferenceView);
		
		if (userPreference == null) {
			setupPreferenceView.displayQuizError("userPrefere = " + userPreference);
		}
		account.setUserPreference(userPreference);
		return true;
	}
	
	/**
	 * Set the context to a certain strategy based on answer
	 * @param answer
	 * @return SetupPreferenceContext either context for manual or quiz strategy based on answer
	 */
	public SetupPreferenceContext setContext(int answer) {
		SetupPreferenceContext context;
		switch(answer) {
		case(0):
			context = new SetupPreferenceContext(new QuizSetupPreferenceStrategy());
			return context;
		case(1):
			context = new SetupPreferenceContext(new ManualSetupPreferenceStrategy());
			return context;
		default:
			return null;
		}
	}
}
