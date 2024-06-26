package edu.mu.finalproject.util;

import java.util.ArrayList;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.view.SetupPreferenceView;

public class ManualSetupPreferenceStrategy implements ISetupPreferenceStrategy {
	private SetupPreferenceView view;	
	private Preference userPreference;

	/**
	 * Prompts for user preference based & assigns preference based on user's 1 choice. Returns the preference matching user’s answer
	 * @param view, will use view to display the question(s) & answers
	 * @return Preference according to user's choice
	 */
	@Override
	public Preference setupPreference(SetupPreferenceView view) {
		if (setView(view)) {
			getView().displayQuestion("What is your listening preference?");
			ArrayList<String> choices = new ArrayList<String>();
			for (Preference preference : Preference.values()) {
				choices.add(preference.capitalizePreference());
			}
			getView().displayChoices(choices);
			int preferenceAnswer = getView().getInputAnswer(choices.size());
			--preferenceAnswer; // Answers are 1-based on UI, but need to get 0-based for ArrayList indexing
			String preferenceStr = choices.get(preferenceAnswer);
			userPreference = Preference.toPreference(preferenceStr);
			getView().displayPreference(preferenceStr);
			// user.setPreference(userPreference);
			return userPreference;
		}
		return null;
	}
// Getters & Setters
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
