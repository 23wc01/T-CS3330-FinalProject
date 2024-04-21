package edu.mu.finalproject.model;

import java.util.Date;

public class Song extends MediaObject {
	private int artistID;
	private Date releaseDate;
	Preference preference;
	
	public Song(int id, String name, String description, Date addedDate, Boolean isFavorited, float rating, int artistID, Preference preference) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.addedDate = addedDate;
		this.isFavorited = isFavorited;
		this.rating = rating;
		this.artistID = artistID;
		this.preference = preference;
	}
	
	public int getArtistID() {
		return artistID;
	}
	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}
	public Preference getPreference() {
		return preference;
	}
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	@Override
	public String toString() {
		return "Song [releaseDate=" + releaseDate + ", name=" + name + ", description=" + description + ", addedDate="
				+ addedDate + ", isFavorited=" + isFavorited + ", rating=" + rating + "]";
	}

	
	

}
