package EventTests;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat.Field;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.mu.finalproject.controller.EventController;
import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventSingleton;
import edu.mu.finalproject.view.EventView;

public class DeleteEventTest {
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        // Reset the eventCollection field to an empty ArrayList<Event> before each test
//        java.lang.reflect.Field field = EventSingleton.class.getDeclaredField("eventCollection");
//        field.setAccessible(true);
//        field.set(null, new ArrayList<>());
//    }
    

    @Test
    public void testViewDeleteEvent_NoEvent() {

        // Act
        int result = EventView.viewDeleteEvent(null);

        // Assert
        assertEquals(-1, result); 
    }
    

    @Test
    public void testDeleteEvent_NoEvent() {

        // Act
        int result = EventController.deleteEvent(null);

        // Assert
        assertEquals(-1, result); 
    }
    

    @Test
    public void testViewDeleteEvent_WithEvents() throws Exception {
        // Arrange
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(MonthDay.of(6, 6), Year.of(2022), "Test Artist 1"));
        events.add(new Event(MonthDay.of(7, 7), Year.of(2023), "Test Artist 2"));
        setEventCollection(events);

        Event eventToBeDeleted = new Event(MonthDay.of(6, 6), Year.of(2022), "Test Artist 1");

        // Act
        int result = EventView.viewDeleteEvent(eventToBeDeleted);

        // Assert
        assertEquals(1, result);
    }
    

    @Test
    public void testDeleteEvent_WithEvents() throws Exception {
        // Arrange
        List<Event> events = new ArrayList<>();
        events.add(new Event(MonthDay.of(6, 6), Year.of(2022), "Test Artist 1"));
        events.add(new Event(MonthDay.of(7, 7), Year.of(2023), "Test Artist 2"));
        setEventCollection(events);

        Event eventToBeDeleted = new Event(MonthDay.of(6, 6), Year.of(2022), "Test Artist 1");

        // Act
        int result = EventController.deleteEvent(eventToBeDeleted);

        // Assert
        assertEquals(1, result);
    }
    
    
    

    private void setEventCollection(List<Event> events) throws Exception {
        java.lang.reflect.Field field = EventSingleton.class.getDeclaredField("eventCollection");
        field.setAccessible(true);
        field.set(null, events);
    }
}