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
	
	// Store the file path to the JSON file and a List of all accounts
	static String path = "files/accounts.json";
	private List<Account> activeAccounts;

	public AccountManager() {
		this.activeAccounts = loadAccounts();
	}
	
	public List<Account> loadAccounts() {
		return activeAccounts;
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
		for (Account account : activeAccounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(hashedPassword)) {
				return account;
			}
		}
		return null; 
	}
	
	public int createAccount() {
		
		return 0;
	}
	
	public int deleteAccount(String username) {
		
		return 0; //on success
	}
	
	private JSONArray extractIDs(List<? extends MediaObject> objects) {
		JSONArray IDs = new JSONArray();
		for (MediaObject object : objects) {
			IDs.put(object.getId());
		}
		return IDs;
	}
	
	private JSONArray extractAccountIDs(List<Account> accounts) {
		JSONArray IDs = new JSONArray();
		for (Account account : accounts) {
			IDs.put(account.getAccountID());
		}
		return IDs;
	}
	
	// This method will write all account information to the accounts.json file.
	public int saveChanges() {
		
		// Create a new JSON array to add Account objects to
		JSONArray accounts = new JSONArray();
		
		// For each account, save all account information into a JSON object and add it to the JSON array.
		// For array list fields, save another JSON array containing the IDs of each object.
		for (Account account : activeAccounts) {
			
			JSONObject a = new JSONObject();
			a.put("accountID", account.getAccountID());
			a.put("username", account.getUsername());
			a.put("password", account.getPassword());
			a.put("savedPlaylistIDs", extractIDs(account.getSavedPlaylists()));
			a.put("savedSongIDs", extractIDs(account.getSavedSongs()));
			a.put("followedArtistIDs", extractIDs(account.getFollowedArtists()));
			a.put("followedUserIDs", extractAccountIDs(account.getFollowedUsers()));
			a.put("preference", account.getUserPreference());
			accounts.put(a);
		}
		
		try {
			Files.write(Paths.get(path), accounts.toString().getBytes());
		} catch (IOException e) {
			return 1; // on failure
		}
		
		return 0; //on success
	}
	

}
