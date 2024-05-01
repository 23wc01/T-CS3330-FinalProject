package EventTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.*;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.util.GetIntegerInput;
import edu.mu.finalproject.view.EventView;


public class DisplayEvents {
	
	//Set up
    EventView eventview = new EventView();
    ArrayList<Event> testCollection = new ArrayList<>();
 

	
	 @Test
	 public void testDisplayEventsByDate_NullInputList() {
	        // Arrange
	        ArrayList<Event> eventsInRange = null;

	        // Act
	        int result = eventview.displayEventsByDate(eventsInRange);

	        // Assert
	        assertEquals(0, result);
	  }
	 
	 

	    @Test
	    public void testDisplayEventsByDate_EmptyInputList() {
	        // Arrange
	        ArrayList<Event> eventsInRange = new ArrayList<>();

	        // Act
	        int result = eventview.displayEventsByDate(eventsInRange);

	        // Assert
	        assertEquals(-1, result);
	    }
	    
	    

	    @Test
	    public void testDisplayEventsByDate_NonEmptyInputList() {
	        // Arrange
	        ArrayList<Event> eventsInRange = new ArrayList<>();
	        eventsInRange.add(new Event(MonthDay.of(6, 6), Year.of(2022), "Artist1"));
	        eventsInRange.add(new Event(MonthDay.of(7, 7), Year.of(2022), "Artist2"));

	        // Act
	        int result = eventview.displayEventsByDate(eventsInRange);

	        // Assert
	        assertEquals(1, result);
	    }


//	    @Test
//	    public void testGetEventDisplayInfo_ValidInput() {
//	        // Arrange 
//	        // Using edge cases
//	    	
//		    String input = "2023\n1\n1\n2023\n12\n31\n";
//	        System.setIn(new ByteArrayInputStream(input.getBytes()));
//	        
//	        // Mock static call for display method
//	        eventview.displayEventsByDate(any(ArrayList.class));
//
//	        // Act
//	        int result = eventview.getEventDisplayInfo();
//
//	        // Assert
//	        eventview.displayEventsByDate(any(ArrayList.class));
//	        assertEquals(1, result);
//	    }

//	    @Test
//	    public void testGetEventDisplayInfo_InvalidDateFormat() {
//	        // Arrange (Mock invalid user input for dates)
//
//	        // Act
//	        int result = EventView.getEventDisplayInfo();
//
//	        // Assert
//	        assertEquals(-1, result);
//	    }

	    @Test
	    public void testGetEventsToDispalyByDate_EventsOutsideDateRange() {
	        // Arrange
	        // Add fake Events to testCollection
	        testCollection.add(new Event(MonthDay.of(6, 6), Year.of(2022), "Test Artist 1"));
	        Year startYear = Year.of(2022);
	        MonthDay startDate = MonthDay.of(6, 1);
	        Year endYear = Year.of(2022);
	        MonthDay endDate = MonthDay.of(6, 5);
	        EventController eventcontroller = new EventController();

	        // Act
	        ArrayList<Event> eventsInRange = eventcontroller.getEventsToDispalyByDate(startYear, startDate, endYear, endDate, testCollection);

	        // Assert
	        assertTrue(eventsInRange.isEmpty());
	    }

	    @Test
	    public void testGetEventsToDispalyByDate_EventsWithinDateRange() { 
	   
	        // Arrange
	    	testCollection.add(new Event(MonthDay.of(7, 31), Year.of(2024), "Test Artist 2"));
	        Year startYear = Year.of(2024);
	        MonthDay startDate = MonthDay.of(7, 1);
	        Year endYear = Year.of(2024);
	        MonthDay endDate = MonthDay.of(8, 31);
	        EventController eventcontroller = new EventController();
	        

	        // Act
	        ArrayList<Event> eventsInRange = eventcontroller.getEventsToDispalyByDate(startYear, startDate, endYear, endDate, testCollection);

	        // Assert
	        assertFalse(eventsInRange.isEmpty());
	    }

}
