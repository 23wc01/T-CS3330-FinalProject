package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.Date;

public class Playlist extends MediaProduct {
	ArrayList<Song> songs = new ArrayList<Song>();

	public Playlist(int id, String name, String description, Date addedDate, Boolean isFavorited,  ArrayList<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.imgDescription = description;
		this.addedDate = addedDate; 
		this.isFavorited = isFavorited;
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
		String playlistStr = "Playlist [name=" + name + ", description=" + imgDescription + ", addedDate="
				+ addedDate.toString() + ", isFavorited=" + isFavorited + "\n\tContains: ";
		if(getSongs() != null) {
			for (Song song : getSongs()) {
				playlistStr += "\n\t";
				playlistStr += song;
			}
		}
		playlistStr += "\n\t]";
		return playlistStr;
	}

	
	
}
