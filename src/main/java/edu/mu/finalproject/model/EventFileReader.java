package edu.mu.finalproject.model;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.MonthDay;
import java.time.Year;
import java.io.IOException;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;
/**
 * This class handles logic related to reading and writing to the file that stores event catalog information
 * 
 * This class is used in UserInterface, and EventController
 * It utilizes the json.org library
 * @author etwil
 */
public class EventFileReader {
	
	private static String path = "files/events.json";

	/**
	 * Reads events from a file
	 * @param filePath
	 * @return ArrayList eventsCollection - an array of all the Events read from the file
	 * @author etwil
	 */
	public static ArrayList<Event> readEvents(String filePath) {
		 
		 ArrayList<Event> eventsCollection = new ArrayList<>();

		 // If no file path is specified, use the default
	     if (filePath == null) {
	            filePath = path;
	     }
		 
	     try {
	         String jsonText = new String(Files.readAllBytes(Paths.get(filePath)));
	         JSONArray jsonArray = new JSONArray(jsonText);
	     
	
	         //Get Event objects and add them to the collection ArrayList
	         for (int i = 0; i < jsonArray.length(); i++) {
	             JSONObject jsonObject = jsonArray.getJSONObject(i);
	             Event event = new Event(
	                 MonthDay.parse("--" + jsonObject.getString("eventMonthDay")),
	                 Year.of(jsonObject.getInt("eventYear")),
	                 jsonObject.getString("artistName")
	             );
	             eventsCollection.add(event);
	         }
	
	         
	     }
		 catch (IOException e) {
				e.printStackTrace();
		 }
	     
	     return eventsCollection;
		
     
	}
	
	/**
	 * Appends specified Event to the end of the default file
	 * @param myEvent - the event that is to be appended to the file
	 * @return and integer representing the result of the operation
	 * 		1 for success
	 *     -1 for Exception, failure within the json.org or java library
	 * @author etwil
	 */
	public static int appendEvent(Event myEvent) {
		try {
		     
            // Read the existing JSON file
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);


            // Convert Event to JSONObject and append it to the JSONArray
            jsonArray.put(myEvent.toJSON());

            // Write the updated array back to the file
            Files.write(Paths.get(path), jsonArray.toString(4).getBytes());  // Beautified print, indent 4
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
	}
	
	
	/**
	 * Overwrites the old file without specified event. Uses fields of the Event object to check if objects are the same
	 * @param MonthDay monthDate
	 * @param Year year
	 * @param String artistName
	 * @return and integer representing the result of the operation
	 * 		1 for success
	 *     -1 for Exception, failure within the json.org or java library
	 * @author etwil
	 */
	public static int deleteEventFromFile(MonthDay monthDate, Year year, String artistName) {
		try {
			
			String jsonText = new String(Files.readAllBytes(Paths.get(path)));//Read original JSON array
			JSONArray jsonArray = new JSONArray(jsonText);
			JSONArray updatedJsonArray = new JSONArray();
			
			//Iterate through and remove matching object
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				//if not the same event, then add it back to the file
				if(!(jsonObject.getString("eventMonthDay").equals(monthDate.toString().replace("--", "")) 
				   && jsonObject.getString("eventYear").equals(year.toString())
				   && jsonObject.getString("artistName").equals(artistName)))
				{
					updatedJsonArray.put(jsonObject);
				}
			}
			
			//Write new array to the file
			Files.write(Paths.get(path), updatedJsonArray.toString(4).getBytes());
			return 1;
		}
		catch(IOException e) {
			e.printStackTrace();
			return -1;
		}
	}




}
