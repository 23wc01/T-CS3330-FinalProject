package edu.mu.finalproject.util;

import java.util.ArrayList;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.view.SetupPreferenceView;

public class ManualSetupPreferenceStrategy implements ISetupPreferenceStrategy {
	private SetupPreferenceView view;	
	private Preference userPreference;

	@Override
	public Preference setupPreference(SetupPreferenceView view) {
		if (view != null) {
			setView(view);
			view.displayQuestion("What is your listening preference?");
			ArrayList<String> choices = new ArrayList<String>();
			for (Preference preference : Preference.values()) {
				choices.add(preference.capitalizePreference());
			}
			view.displayChoices(choices);
			int preferenceAnswer = view.getInputAnswer(choices.size());
			--preferenceAnswer; // Answers are 1-based on UI, but need to get 0-based for ArrayList indexing
			String preferenceStr = choices.get(preferenceAnswer);
			userPreference = Preference.toPreference(preferenceStr);
			view.displayPreference(preferenceStr);
			// user.setPreference(userPreference);
			return userPreference;
		}
		return null;
	}
	private void setView(SetupPreferenceView view) {
		this.view = view;
	}
}
