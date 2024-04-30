package EventTests;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import org.mockito.*;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.view.EventView;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest(EventSingleton.class)
public class AddEventTest {
	
	@AfterClass
	public static void deleteAddedTestEvents() {
		// Create the two sample events that will be used in tests
		Event myEvent1 = new Event(MonthDay.of(6, 6), Year.of(2022), "ME:I");
		Event myEvent2 = new Event(MonthDay.of(7, 7), Year.of(2022), "JP THE WAYV");
		
		//Delete the test events so the file remains the same
		EventView.viewDeleteEvent(myEvent1);
		EventView.viewDeleteEvent(myEvent2);
	}
	
	
	@Test
    public void testViewAddEvent_Success() {
        // Create a sample event
        Event myEvent = new Event(MonthDay.of(6, 6), Year.of(2022), "ME:I");

        // Call the method and check the return value
        int result = EventView.viewAddEvent(myEvent);

        // Verify that the event was added successfully
        assertEquals(1, result, "Expected return value to indicate success");
    }
	

    @Test
    public void testViewAddEvent_AlreadyExists() {
        // Create a sample event
        Event myEvent = new Event(MonthDay.of(6, 6), Year.of(2022), "ME:I");

        // Call the method twice with the same event
        int result1 = EventView.viewAddEvent(myEvent);
        int result2 = EventView.viewAddEvent(myEvent);

        // Verify that the event already exists
        assertEquals(0, result2, "Expected return value to indicate that event already exists");
    }
    

    @Test
    public void testViewAddEvent_NullEvent() {
        // Call the method with null event
        int result = EventView.viewAddEvent(null);

        // Verify that it returns -1 for null event
        assertEquals(-1, result, "Expected return value to indicate failure for null event");
    }
    
    
    @Test
    public void testAddEvent_Success() {
    	// Create a sample event
        Event myEvent = new Event(MonthDay.of(7, 7), Year.of(2022), "JP THE WAYV");
        
        // Call the method and check the return value
        int result = EventController.addEvent(myEvent);

        // Verify that the event was added successfully
        assertEquals(1, result, "Expected return value to indicate success");
        
    }
    
    @Test
    public void testAddEvent_AlreadyExists() {
    	// Create a sample event
        Event myEvent = new Event(MonthDay.of(6, 6), Year.of(2022), "ME:I");

        // Call the method twice with the same event
        int result1 = EventController.addEvent(myEvent);
        int result2 = EventController.addEvent(myEvent);

        // Verify that the event already exists
        assertEquals(0, result2, "Expected return value to indicate that event already exists");
    }
    
    
//    @Test
//    public void testAddEvent_FailureInAppendEvent() {
//    	// Arrange
//        Event myEvent = new Event(MonthDay.of(6, 6), Year.of(2022), "ME:I");
//
//        // Mocking EventSingleton.getEventCollection() to return an empty list 
//        PowerMockito.mockStatic(EventSingleton.class);
//        when(EventSingleton.getEventCollection()).thenReturn(new ArrayList<>());
//
//        // Mocking EventFileReader.appendEvent(myEvent) to return failure
//        when(EventFileReader.appendEvent(myEvent)).thenReturn(-1);
//
//        // Act
//        int result = EventController.addEvent(myEvent);
//
//        // Assert
//        assertEquals(-1, result);
//    }
    

}


