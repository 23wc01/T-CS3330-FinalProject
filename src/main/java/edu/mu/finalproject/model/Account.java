package edu.mu.finalproject.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a user account in the application.
 */
public class Account {

	int accountID;
	String username;
	String password;
	List<String> savedPlaylists;
	List<String> savedSongs;
	List<String> followedUsers;
	Preference userPreference;
	
	/**
	 * Constructs a new Account instance for new accounts.
	 * 
	 * @param accountID The unique identifier of the account.
	 * @param username The username of the new account.
	 * @param password The password of the new account.
	 */
	public Account(int accountID, String username, String password) {
		this.accountID= accountID;
		this.username = username;
		this.password = password;
		this.savedPlaylists = new ArrayList<String>();
		this.savedSongs = new ArrayList<String>();
		this.followedUsers = new ArrayList<String>();
		this.userPreference = null;
	}
	
	/**
	 * Constructs a new account instance for accounts loaded from the JSON file.
	 * 
	 * @param accountID The unique identifier of the account.
	 * @param username The username of the account.
	 * @param password The password of the account.
	 * @param savedPlaylists The list of saved playlists for the account.
	 * @param savedSongs The list of saved songs for the account.
	 * @param followedUsers The list of users followed by the account.
	 * @param userPreference The preference setting for the account.
	 */
	public Account(int accountID, String username, String password, List<String> 
	savedPlaylists, List<String> savedSongs, List<String> followedUsers,
	Preference userPreference) {
		this.accountID= accountID;
		this.username = username;
		this.password = password;
		this.savedPlaylists = savedPlaylists;
		this.savedSongs = savedSongs;
		this.followedUsers = followedUsers;
		this.userPreference = userPreference;
	}

	//Getters and setters for all Account parameters:
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password; 
	}

	public List<String> getSavedPlaylists() {
		return savedPlaylists;
	}

	public void setSavedPlaylists(List<String> savedPlaylists) {
		this.savedPlaylists = savedPlaylists;
	}

	public List<String> getSavedSongs() {
		return savedSongs;
	}

	public void setSavedSongs(List<String> savedSongs) {
		this.savedSongs = savedSongs;
	}

	public List<String> getFollowedUsers() {
		return followedUsers;
	}

	public void setFollowedUsers(List<String> followedUsers) {
		this.followedUsers = followedUsers;
	}

	public Preference getUserPreference() {
		return userPreference;
	}

	public void setUserPreference(Preference userPreference) {
		this.userPreference = userPreference;
	}

	public int getAccountID() {
		return accountID;
	}
	
	/**
	 * Returns a string representation of the account.
	 * 
	 * @return A string containing all account field information.
	 */
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", username=" + username + ", savedPlaylists=" + savedPlaylists
				+ ", savedSongs=" + savedSongs + ", followedUsers=" + followedUsers + ", userPreference=" + userPreference + "]";
	}
	
	
}
