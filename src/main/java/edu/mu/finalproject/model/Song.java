package edu.mu.finalproject.model;

import java.util.Date;

/**
 * The Song class represents a song in a media library and extends the MediaProduct class.
 * It includes attributes specific to songs like the artist's name and the song's release date.
 * Used to manage song data like preferences associated with the song.
 * 
 * This class provides necessary getter and setter functions, as well as a toString
 * 
 * It works closely with Preference
 */
public class Song extends MediaProduct {
	private String artistName;
	private Date releaseDate;
	Preference preference;
	
	public Song(int id, String name, String description, Boolean isFavorited, Date addedDate, String artistName, Preference preference) {
		super();
		this.id = id;
		this.name = name;
		this.imgDescription = description;
		this.addedDate = addedDate;
		this.isFavorited = isFavorited;
		this.artistName = artistName;
		this.preference = preference;
	}
	
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public Preference getPreference() {
		return preference;
	}
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	@Override
	public String toString() {
		return "Song [releaseDate=" + releaseDate + ", name=" + name + ", description=" + imgDescription + ", addedDate="
				+ addedDate + ", isFavorited=" + isFavorited +", artistName=" + artistName + ", Preference: " + preference.capitalizePreference() + "]";
	}

	
	

}
