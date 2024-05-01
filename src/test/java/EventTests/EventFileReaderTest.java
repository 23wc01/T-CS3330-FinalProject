package EventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;


@OrderWith(Alphanumeric.class)
public class EventFileReaderTest {
	
	private static final String FILE_PATH = "files/events.json";
	
//	@BeforeEach
//	public void setUp() {
//		System.setProperty("EVENTS_FILE_PATH", "files/eventstest.json");
//	}
//
    @Test
    public void test1ReadEvents() { 
 
        // Arrange
        ArrayList<Event> expectedEvents = new ArrayList<>();

        //Remove these three if you clear events.json
        expectedEvents.add(new Event(MonthDay.of(12, 31), Year.of(2026), "Test Artist 1"));
        expectedEvents.add(new Event(MonthDay.of(1, 1), Year.of(2025), "Test Artist 2"));
        

        // Act
        ArrayList<Event> actualEvents = EventFileReader.readEvents("files/eventstest.json");

        // Assert
        assertEquals(expectedEvents.size(), actualEvents.size(), "Size expected to be 2"); //subtract 3 if events.json is cleared
        assertTrue(actualEvents.containsAll(expectedEvents));
    }
    

    @Test
    public void test2AppendEvent() {
    	System.out.println("in test append");
    	
        // Arrange
        Event myEvent = new Event(MonthDay.of(8, 8), Year.of(2024), "TestIVE");

        // Act
        int result = EventFileReader.appendEvent(myEvent);

        // Assert
        assertEquals(1, result);
        assertTrue(Files.exists(Paths.get(FILE_PATH)));
    }
    
    @Test
    public void testAppendEventException() {
    	
        // Arrange
//        Event myEvent = new Event(MonthDay.of(8, 8), Year.of(2024), "TestIVE");

        // Act
        int result = EventFileReader.appendEvent(null);

        // Assert
        assertEquals(-1, result);
//        assertTrue(Files.exists(Paths.get(FILE_PATH)));
    }

    
    @Test
    public void test3DeleteEventFromFile() {
        // Arrange
        MonthDay monthDate = MonthDay.of(8, 8);
        Year year = Year.of(2024);
        String artistName = "TestIVE";

        // Act
        int result = EventFileReader.deleteEventFromFile(monthDate, year, artistName);

        // Assert
        assertEquals(1, result);
        assertFalse(getFileContent(FILE_PATH).contains(artistName));

    }
    
//    
//    @After
//    public void tearDown() {
//    	System.clearProperty("EVENTS_FILE_PATH");
//    }
    

    private String getFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
