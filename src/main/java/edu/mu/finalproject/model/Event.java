package edu.mu.finalproject.model;

import java.time.MonthDay;
import java.time.Year;

public class Event {
	
	protected MonthDay eventMonthDay;
	protected Year eventYear;
	
	protected String artistName;
	
	

	public MonthDay getEventMonthDay() {
		return eventMonthDay;
	}
	public void setEventMonthDay(MonthDay eventMonthDay) {
		this.eventMonthDay = eventMonthDay;
	}
	
	

	public Year getEventYear() {
		return eventYear;
	}
	public void setEventYear(Year eventYear) {
		this.eventYear = eventYear;
	}
	
	

	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

		
		
		
}
