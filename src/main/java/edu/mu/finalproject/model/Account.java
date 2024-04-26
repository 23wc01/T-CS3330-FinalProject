package edu.mu.finalproject.model;

import java.util.List;

public class Account {

	int accountID;
	String username;
	String password;
	List<Playlist> savedPlaylists;
	List<Song> savedSongs;
	List<String> followers;
	List<String> following;
	Preference userPreference;
	
	// CONSTRUCTOR
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// GETTERS AND SETTERS
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Playlist> getSavedPlaylists() {
		return savedPlaylists;
	}
	public void setSavedPlaylists(List<Playlist> savedPlaylists) {
		this.savedPlaylists = savedPlaylists;
	}
	public List<Song> getSavedSongs() {
		return savedSongs;
	}
	public void setSavedSongs(List<Song> savedSongs) {
		this.savedSongs = savedSongs;
	}
	public List<Album> getSavedAlbums() {
		return savedAlbums;
	}
	public void setSavedAlbums(List<Album> savedAlbums) {
		this.savedAlbums = savedAlbums;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	public List<String> getFollowing() {
		return following;
	}
	public void setFollowing(List<String> following) {
		this.following = following;
	}
	public Preference getUserPreference() {
		return userPreference;
	}
	public void setUserPreference(Preference userPreference) {
		this.userPreference = userPreference;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", username=" + username + ", savedPlaylists=" + savedPlaylists
				+ ", savedSongs=" + savedSongs + ", followers=" + followers
				+ ", following=" + following + ", userPreference=" + userPreference + "]";
	}
	
}
