package edu.mu.finalproject.model;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.MonthDay;
import java.time.Year;
import java.io.IOException;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;

public class EventFileReader {
	
	static String path = "files/events.json";

	public static ArrayList<Event> readEvents() {
		 
		 ArrayList<Event> eventsCollection = new ArrayList<>();
		 
	     try {
	         String jsonText = new String(Files.readAllBytes(Paths.get(path)));
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
	
	
	public static void appendEvent(Event myEvent) {
		try {
            // Read the existing JSON file
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);


            // Convert Event to JSONObject and append it to the JSONArray
            jsonArray.put(myEvent.toJSON());

            // Write the updated array back to the file
            Files.write(Paths.get(path), jsonArray.toString(4).getBytes());  // Beautified print, indent 4
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void deleteEvent(MonthDay monthDate, Year year, String artistName) {
		try {
			
			System.out.println("" + monthDate +" "+ year +" "+ artistName);
	
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
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
