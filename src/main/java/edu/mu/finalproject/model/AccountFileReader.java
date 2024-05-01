package edu.mu.finalproject.model;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccountFileReader {
	
	static String path = "files/accounts.json"; // file path to JSON file

	public AccountFileReader() {
		// TODO Auto-generated constructor stub
	}
	
	// This method will load in any accounts stored in the JSON file and place them in an Account List
	public List<Account> loadAccounts() {
			
		List<Account> accounts = new ArrayList<Account>();
			
		try {
			String accountInformation = new String(Files.readAllBytes(Paths.get(path)));
			JSONArray accountsJSON = new JSONArray(accountInformation);
				
			for (int i = 0; i<accountsJSON.length(); i++) {
					
				// Get account fields stored in each JSON object 
				JSONObject accountJSON = accountsJSON.getJSONObject(i);
				int accountID = accountJSON.getInt("accountID");
				String username = accountJSON.getString("username");
				String password = accountJSON.getString("password");
				JSONArray savedPlaylistsJSON = accountJSON.getJSONArray("savedPlaylistIDs");
				JSONArray savedSongsJSON = accountJSON.getJSONArray("savedSongIDs");
				JSONArray followedArtistsJSON = accountJSON.getJSONArray("followedArtistIDs");
				JSONArray followedUsersJSON = accountJSON.getJSONArray("followedUserIDs");
				String preference = accountJSON.getString("Preference");
				Preference userPreference = Preference.valueOf(preference);
				
				List<String> savedPlaylists = loadStringArray(savedPlaylistsJSON);
				List<String> savedSongs = loadStringArray(savedSongsJSON);
				List<String> followedArtists = loadStringArray(followedArtistsJSON);
				List<String> followedUsers = loadStringArray(followedUsersJSON);
				
				// Create a new account object from information in JSON file and add it to the accounts List
				Account account = new Account(accountID, username, password, savedPlaylists, savedSongs, followedArtists, followedUsers, userPreference);
				accounts.add(account);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return accounts;
	}	
	
	
	// This method will write all account information from the accounts field to the accounts.json file.
	public int saveAccounts(List<Account> accounts) {
		
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
			a.put("followedArtistIDs", saveStringArray(account.getFollowedArtists()));
			a.put("followedUserIDs", saveStringArray(account.getFollowedUsers()));
			a.put("preference", account.getUserPreference());
			accountsJSON.put(a);
		}
		
		try {
			Files.write(Paths.get(path), accounts.toString().getBytes());
		} catch (IOException e) {
			return 1; // on failure
		}
		
		return 0; //on success
	}
	
	// This method returns a List of Strings given a JSON array of strings.
		public List<String> loadStringArray(JSONArray stringsJSON) {
			List<String> strings = new ArrayList<String>();
			for (int i = 0; i < stringsJSON.length(); i++) {
				String string = stringsJSON.getString(i);
				strings.add(string);
			}
			return strings;
		}
		
	// This method returns a JSON array of the names of a list of Strings.
	public JSONArray saveStringArray(List<String> strings) {
		JSONArray stringsJSON = new JSONArray();
		for (String string : strings) {
			stringsJSON.put(string);
		}
		return stringsJSON;
	}

}
