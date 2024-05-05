package edu.mu.finalproject.model;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Utility class for reading and writing account information to and from a JSON file.
 */
public class AccountFileReader {
	
	/**
	 * File path to the JSON file that stores account information.
	 */
	static String path = "files/accounts.json";

	public AccountFileReader() {
		
	}
	
	/**
	 * Loads account information from the JSON file.
	 *  
	 * @return A list of Account objects loaded in from the JSON file.
	 */
	public static List<Account> loadAccounts() {
			
		List<Account> accounts = new ArrayList<Account>();
			
		try {
			String accountInformation = new String(Files.readAllBytes(Paths.get(path)));
			JSONArray accountsJSON = new JSONArray(accountInformation);
				
			for (int i = 0; i<accountsJSON.length(); i++) {
					
				// Get account fields stored in each JSON object. 
				JSONObject accountJSON = accountsJSON.getJSONObject(i);
				int accountID = accountJSON.getInt("accountID");
				String username = accountJSON.getString("username");
				String password = accountJSON.getString("password");
				JSONArray savedPlaylistsJSON = accountJSON.getJSONArray("savedPlaylistIDs");
				JSONArray savedSongsJSON = accountJSON.getJSONArray("savedSongIDs");
				JSONArray followedUsersJSON = accountJSON.getJSONArray("followedUserIDs");
				String preference = accountJSON.getString("preference");
				Preference userPreference;
				if (preference.equals("null")) {
					userPreference = null;
				}
				else {
					userPreference = Preference.valueOf(preference);
				}
				
				List<String> savedPlaylists = loadStringArray(savedPlaylistsJSON);
				List<String> savedSongs = loadStringArray(savedSongsJSON);
				List<String> followedUsers = loadStringArray(followedUsersJSON);
				
				// Create a new account object from information in JSON file and add it to the accounts List.
				Account account = new Account(accountID, username, password, savedPlaylists, savedSongs, followedUsers, userPreference);
				accounts.add(account);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return accounts;
	}	
	
	/**
	 * Writes account information from the provided list to the JSON file.
	 * 
	 * @param accounts The list of Account objects to be saved to the JSON.
	 * @return 0 if the accounts are successfully saved, 1 if an error occurs.
	 */
	public static int saveAccounts(List<Account> accounts) {
		
		// Create a new JSON array to add Account objects to
		JSONArray accountsJSON = new JSONArray();
		
		// For each account, save all account information into a JSON object and add it to the JSON array.
		// For array list fields, save another JSON array containing the IDs of each object.
		for (Account account : accounts) {
			
			JSONObject a = new JSONObject();
			a.put("accountID", account.getAccountID());
			a.put("username", account.getUsername());
			a.put("password", account.getPassword());
			a.put("savedPlaylistIDs", saveStringArray(account.getSavedPlaylists()));
			a.put("savedSongIDs", saveStringArray(account.getSavedSongs()));
			a.put("followedUserIDs", saveStringArray(account.getFollowedUsers()));
			if (account.getUserPreference() == null) {
				a.put("preference", "null");
			}
			else {
				a.put("preference", account.getUserPreference().name());
			}
			accountsJSON.put(a);
		}
		
		try {
			Files.write(Paths.get(path), accountsJSON.toString().getBytes());
		} catch (IOException e) {
			return 1; // on failure
		}
		
		return 0; //on success
	}
	
	/**
	 * Converts a JSON array of strings to a List of strings.
	 * 
	 * @param stringsJSON The JSON array of strings to be converted.
	 * @return A list of strings converted from the JSON array.
	 */
	public static List<String> loadStringArray(JSONArray stringsJSON) {
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < stringsJSON.length(); i++) {
			String string = stringsJSON.getString(i);
			strings.add(string);
		}
		return strings;
	}
		
	/**
	 * Converts a list of strings to a JSON array of strings. 
	 * 
	 * @param strings The list of strings to be converted.
	 * @return A JSON array of strings converted from the list.
	 */
	public static JSONArray saveStringArray(List<String> strings) {
		JSONArray stringsJSON = new JSONArray();
		for (String string : strings) {
			stringsJSON.put(string);
		}
		return stringsJSON;
	}

}
