// Source(s): https://howtodoinjava.com/java/enum/enum-with-multiple-values/
package edu.mu.finalproject.model;

public enum Preference {
	BASS("Bass", 0, 10),
	POP("Pop", 10, 20),
	CHILL("Pop", 20,30),
	BOOSTED("Boosted", 30, 40),
	SOFT("Soft", 40,50);
	private final String preferenceName; 
	private final int rangeMin; 
	private final int rangeMax;
	Preference(String preferenceName, int rangeMin, int rangeMax) {
		this.preferenceName = preferenceName;
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}
	public String getPreferenceName() {
		return preferenceName;
	}
	public int getRangeMin() {
		return rangeMin;
	}
	public int getRangeMax() {
		return rangeMax;
	}
}
