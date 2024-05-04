package edu.mu.finalproject.util;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.view.SetupPreferenceView;

public class SetupPreferenceContext {
	private ISetupPreferenceStrategy strategy;

	public SetupPreferenceContext(ISetupPreferenceStrategy strategy) {
		this.strategy = strategy;
	}
	public Preference updatePreference(SetupPreferenceView view) {
		if (view == null) {
			return null;
		}
		return strategy.setupPreference(view);
	}
}
