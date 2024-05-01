package edu.mu.finalproject.model;

import java.util.List;
import java.util.ArrayList;

public class Account {

	int accountID;
	String username;
	String password;
	List<String> savedPlaylists;
	List<String> savedSongs;
	List<String> followedArtists;
	List<String> followedUsers;
	Preference userPreference;
	
	// CONSTRUCTOR FOR NEW ACCOUNTS
	public Account(int accountID, String username, String password) {
		this.accountID= accountID;
		this.username = username;
		this.password = password;
		this.savedPlaylists = new ArrayList<String>();
		this.savedSongs = new ArrayList<String>();
		this.followedArtists = new ArrayList<String>();
		this.followedUsers = new ArrayList<String>();
		this.userPreference = null;
	}
	
	// CONSTRUCTOR FOR LOADED IN ACCOUNTS
	public Account(int accountID, String username, String password, List<String> 
	savedPlaylists, List<String> savedSongs, List<String> followedArtists, List<String> followedUsers,
	Preference userPreference) {
		this.accountID= accountID;
		this.username = username;
		this.password = password;
		this.savedPlaylists = savedPlaylists;
		this.savedSongs = savedSongs;
		this.followedArtists = followedArtists;
		this.followedUsers = followedUsers;
		this.userPreference = userPreference;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	protected void setPassword(String password) {
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

	public List<String> getFollowedArtists() {
		return followedArtists;
	}

	public void setFollowedArtists(List<String> followedArtists) {
		this.followedArtists = followedArtists;
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
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", username=" + username + ", savedPlaylists=" + savedPlaylists
				+ ", savedSongs=" + savedSongs + ", followedArtists=" + followedArtists
				+ ", followedUsers=" + followedUsers + ", userPreference=" + userPreference + "]";
	}
}
