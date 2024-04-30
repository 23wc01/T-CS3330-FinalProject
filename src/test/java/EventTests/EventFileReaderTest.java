package EventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

import edu.mu.finalproject.model.Event;
import edu.mu.finalproject.model.EventFileReader;


@OrderWith(Alphanumeric.class)
public class EventFileReaderTest {
	
	private static final String FILE_PATH = "files/events.json";
	

    @Test
    public void test1ReadEvents() {
 
        // Arrange
        ArrayList<Event> expectedEvents = new ArrayList<>();
        //Remove these three if you clear events.json
        expectedEvents.add(new Event(MonthDay.of(12, 25), Year.of(2023), "New Jeans"));
        expectedEvents.add(new Event(MonthDay.of(7, 4), Year.of(2024), "Twice"));
        expectedEvents.add(new Event(MonthDay.of(10, 31), Year.of(2025), "Stray Kids"));
        

        // Act
        ArrayList<Event> actualEvents = EventFileReader.readEvents();

        // Assert
        assertEquals(expectedEvents.size(), actualEvents.size(), "Size expected to be 3"); //subtract 3 if events.json is cleared
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
    
    
    
    

    private String getFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
