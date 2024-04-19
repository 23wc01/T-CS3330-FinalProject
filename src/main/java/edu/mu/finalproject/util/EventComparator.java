package edu.mu.finalproject.util;

import java.util.Comparator;

import edu.mu.finalproject.model.Event;

public class EventComparator implements Comparator<Event>{
	
	@Override
	public int compare(Event event1, Event event2) {
		
		int yearComp = event1.getEventYear().compareTo(event2.getEventYear());
		
		//If years are different, return comparison result
		if (yearComp != 0) {
			return yearComp;
		}
		
		//IF years are the same, compare month and day
		return event1.getEventMonthDay().compareTo(event2.getEventMonthDay());
	}

}
