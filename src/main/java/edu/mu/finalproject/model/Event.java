package edu.mu.finalproject.model;

import java.time.MonthDay;
import java.time.Year;

public class Event {
	
	protected MonthDay eventMonthDay;
	protected Year eventYear;
	
	protected String artistName;
	
	
	//Parameterized Constructor
	public Event(MonthDay eventMonthDay, Year eventYear, String artistName) {
		super();
		this.eventMonthDay = eventMonthDay;
		this.eventYear = eventYear;
		this.artistName = artistName;
	}
	
	//No use for copy constructor
	
	
	
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

	@Override
	public String toString() {
		return "Event [eventMonthDay=" + eventMonthDay + ", eventYear=" + eventYear + ", artistName=" + artistName
				+ "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        return eventMonthDay.equals(other.eventMonthDay) &&
                eventYear.equals(other.eventYear) &&
                artistName.equalsIgnoreCase(other.artistName);
    }


		
		
		
}
