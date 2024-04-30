package edu.mu.finalproject.model;

import java.util.List;
import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

// This class manages the account information stored in the account JSON file.
// It is used to create new accounts, load in the accounts, and to append or delete accounts.
public class AccountManager {
	
	
	static String path = "files/accounts.json"; // file path to JSON file
	private List<Account> accounts;				// List of all application accounts
	private int accountCounter;					// Increments with creation of each new account
	
	
	// CONSTRUCTOR 
	public AccountManager() {
		this.accounts = loadAccounts();
		this.accountCounter = getLastAccountID(this.accounts) + 1;
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
	
	// This method returns the account ID of the most recently created account.
	// If there have not been any accounts created, it returns 0.
	private int getLastAccountID(List<Account> accounts) {
		if (accounts.size() == 0) {
			return 0;
		}
		int lastAccountIndex = accounts.size()-1;
		return accounts.get(lastAccountIndex).getAccountID();
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
	
	// This method uses a hash function to protect the user passwords
	private String hashPassword(String passwordToHash) {
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hashedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		return hashedPassword;
	}
	
	// This method is used by the login controller to login a user.
	// If the user successfully logs in, it returns their account object, otherwise it returns null.
	public Account authenticateUser(String username, String password) {
		String hashedPassword = hashPassword(password);
		for (Account account : this.accounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(hashedPassword)) {
				return account;
			}
		}
		return null; 
	}
	
	// This method adds a new account to the accounts List.
	public Account createAccount(String username, String password) {
		Account account = new Account(this.accountCounter, username, password);
		this.accountCounter++; 		
		this.accounts.add(account);
		return account;
	}
	
	// This method checks if a username is already taken
	public boolean isUsernameTaken(String username) {
		for (Account account: this.accounts) {
			if (account.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	// This method deletes an account with a given username from the accounts List.
	// It returns 0 on success and 1 on failure.
	public int deleteAccount(String username) {
		for (int i = 0; i < this.accounts.size(); i++) {
	        Account account = this.accounts.get(i);
	        if (account.getUsername().equals(username)) {
	            this.accounts.remove(i);
	            return 0;
	        }
	    }
	    return 1;
	}
	
	// This method returns a JSON array of the names of a list of Strings.
	public JSONArray stringToJSON(List<String> strings) {
		JSONArray stringsJSON = new JSONArray();
		for (String string : strings) {
			stringsJSON.put(string);
		}
		return stringsJSON;
	}
	
	// This method will write all account information from the accounts field to the accounts.json file.
	public int saveAccounts() {
		
		// Create a new JSON array to add Account objects to
		JSONArray accountsJSON = new JSONArray();
		
		// For each account, save all account information into a JSON object and add it to the JSON array.
		// For array list fields, save another JSON array containing the IDs of each object.
		for (Account account : accounts) {
			
			JSONObject a = new JSONObject();
			a.put("accountID", account.getAccountID());
			a.put("username", account.getUsername());
			a.put("password", account.getPassword());
			a.put("savedPlaylistIDs", stringToJSON(account.getSavedPlaylists()));
			a.put("savedSongIDs", stringToJSON(account.getSavedSongs()));
			a.put("followedArtistIDs", stringToJSON(account.getFollowedArtists()));
			a.put("followedUserIDs", stringToJSON(account.getFollowedUsers()));
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
	

}
