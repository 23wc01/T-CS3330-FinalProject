package edu.mu.finalproject.model;

import java.time.MonthDay;
import java.time.Year;
import java.util.Collection;

import org.json.JSONObject;

/**
 * The Event class represents a music event, such as a concert. It holds details like
 * the date of the event and artist's name
 * 
 * It works closely with EventView.
 */
public class Event {
	
	
	protected MonthDay eventMonthDay;
	protected Year eventYear;
	protected String artistName;
	
	
	//Parameterized Constructors
	public Event(MonthDay eventMonthDay, Year eventYear, String artistName) {
		this.eventMonthDay = eventMonthDay;
		this.eventYear = eventYear;
		this.artistName = artistName;
	}
	
	
	//No use for copy constructor
	
	
	
	public MonthDay getEventMonthDay() {
		return eventMonthDay;
	}
	
	

	public Year getEventYear() {
		return eventYear;
	}

	
	

	public String getArtistName() {
		return artistName;
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

	/**
	 * Creates a new json object, and copies info from the Event object's fields to the json object
	 * @param none
	 * @return jsonObject 
	 * @author etwil
	 */
	 public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eventMonthDay", eventMonthDay.toString().replace("--", "")); // Format "MM-DD"
        jsonObject.put("eventYear", eventYear.toString()); // Format "YYYY"
        jsonObject.put("artistName", artistName);
        return jsonObject;
    }


		
		
		
}
