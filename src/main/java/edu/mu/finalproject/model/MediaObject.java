package edu.mu.finalproject.model;

import java.util.Date;

public abstract class MediaObject {
	protected int id;
	protected String name;
	protected String description;
	protected Date addedDate;
	protected Boolean isFavorited;
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
	
}
