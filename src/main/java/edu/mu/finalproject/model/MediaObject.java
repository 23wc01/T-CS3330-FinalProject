package edu.mu.finalproject.model;

import java.util.Date;
import java.util.Objects;

public abstract class MediaObject {
	protected int id;
	protected String name;
	protected String description;
	protected Date addedDate;
	protected Boolean isFavorited = false;
	protected float rating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public Boolean getIsFavorited() {
		return isFavorited;
	}
	public void setIsFavorited(Boolean isFavorited) {
		this.isFavorited = isFavorited;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
	public boolean toggleFavorite() {
		
		if(this.isFavorited == true) {
			this.setIsFavorited(false);
			return false;
		}
		else {
			this.setIsFavorited(true);
			return true;
		}

		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(addedDate, description, id, isFavorited, name, rating);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaObject other = (MediaObject) obj;
		return Objects.equals(addedDate, other.addedDate) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(isFavorited, other.isFavorited) && Objects.equals(name, other.name)
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating);
	}
	
	
	
}
