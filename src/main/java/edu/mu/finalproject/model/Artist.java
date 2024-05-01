package edu.mu.finalproject.model;

import java.util.Date;
import java.util.List;

public class Artist extends MediaProduct{

		protected int artistID;
		//Album list?
		
		public Artist(int id, String name, String description, Date addedDate, Boolean isFavorited, float rating, int artistID) {
			super();
			this.id = id;
			this.name = name;
			this.imgDescription = description;
			this.releaseDate = addedDate;
			this.isFavorited = isFavorited;
			this.artistID = artistID;
		}
		
}
