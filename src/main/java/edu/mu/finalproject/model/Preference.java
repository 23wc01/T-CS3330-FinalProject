// Source(s): https://howtodoinjava.com/java/enum/enum-with-multiple-values/
package edu.mu.finalproject.model;

import org.apache.commons.lang3.StringUtils;

public enum Preference {
	RAP,
	POP,
	EDM,
	ROCK,
	SOUL,
	COUNTRY;
	/**
	 * Properly capitalizes enum value. Ex: SOUL returns "Soul"
	 * @return
	 */
	public String capitalizePreference() {
		return StringUtils.capitalize(this.toString().toLowerCase());
	}
	
	/**
	 * Converts string of enum (in any form of capitalization format) to its enum value. Ex: "Soul" becomes SOUL
	 * @param preferenceStr
	 * @return
	 */
	public static Preference toPreference(String preferenceStr) {
		return Preference.valueOf(preferenceStr.toUpperCase());
	}
}
