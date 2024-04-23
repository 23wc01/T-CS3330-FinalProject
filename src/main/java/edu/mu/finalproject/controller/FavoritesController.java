package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaObject;

public class FavoritesController {
	
	public static ArrayList<MediaObject> gatherFavorited(Class<? extends MediaObject> type) {
		
		ArrayList <MediaObject> favorites = new ArrayList<MediaObject>();
		
		for (MediaObject media : catalog) { //REPLACE CATALOG
			if (media.getClass() == type) { //Use .getClass() not instanceOf because we don't want to count subclasses
				if (media.getIsFavorited()) {
					favorites.add(media);
				}
			}
		}
		
		return favorites;
		
	}

}
