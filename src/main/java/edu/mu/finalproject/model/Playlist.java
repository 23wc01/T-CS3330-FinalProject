package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.Date;

public class Playlist extends MediaObject {
	ArrayList<Song> songs = new ArrayList<Song>();

	public Playlist(int id, String name, String description, Date addedDate, Boolean isFavorited, float rating,  ArrayList<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.addedDate = addedDate; 
		this.isFavorited = isFavorited;
		this.rating = rating;
		this.songs = songs;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Playlist [name=" + name + ", description=" + description + ", addedDate="
				+ addedDate + ", isFavorited=" + isFavorited + ", rating=" + rating + "\nPlaylist " + name + " contains: " + songs + "]";
	}

	
	
}
