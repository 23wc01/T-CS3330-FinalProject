package edu.mu.finalproject.util;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.view.SetupPreferenceView;

public class SetupPreferenceContext {
	private ISetupPreferenceStrategy strategy;
	/**
	 * Constructor. Context created must have a @strategy
	 * @param strategy
	 */
	public SetupPreferenceContext(ISetupPreferenceStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Passes view into setupPreference so that questions & answers can be displayed properly
	 * @param view
	 * @return
	 */
	public Preference updatePreference(SetupPreferenceView view) {
		return strategy.setupPreference(view);
	}
}
