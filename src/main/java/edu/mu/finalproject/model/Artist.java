package edu.mu.finalproject.model;

import java.util.Date;
import java.util.List;

public class Artist extends MediaObject{

		protected int artistID;
		//Album list?
		
		public Artist(int id, String name, String description, Date addedDate, Boolean isFavorited, float rating, int artistID) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.addedDate = addedDate;
			this.isFavorited = isFavorited;
			this.artistID = artistID;
		}
		
}
