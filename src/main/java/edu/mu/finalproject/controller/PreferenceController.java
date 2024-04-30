// Source(s): https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
package edu.mu.finalproject.controller;


import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;
import edu.mu.finalproject.view.SetupPreferenceView;

public class PreferenceController {
	private SetupPreferenceView setupPreferenceView;

	private Preference userPreference;

	
	public PreferenceController() {
		this.setupPreferenceView = new SetupPreferenceView();	
	}
	
	/**
	 * Setup user listening preference in 1 of 2 strategies
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
	
}
