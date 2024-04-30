package edu.mu.finalproject.model;

import java.util.Date;
import java.util.Objects;

public abstract class MediaProduct {
	protected int id;
	protected String name;
	protected String imgDescription;
	protected Date addedDate;
	protected Boolean isFavorited = false;
	
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
	public String getImgDescription() {
		return imgDescription;
	}
	public void setImgDescription(String description) {
		this.imgDescription = description;
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
		return Objects.hash(addedDate, imgDescription, id, isFavorited, name);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaProduct other = (MediaProduct) obj;
		return Objects.equals(addedDate, other.addedDate) && Objects.equals(imgDescription, other.imgDescription)
				&& id == other.id && Objects.equals(isFavorited, other.isFavorited) && Objects.equals(name, other.name);
	}
	
	
	
}