package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;

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
	
	
	
	
	
	public static boolean favorite(MediaObject objectToBeFavorited) {
	
		boolean result = objectToBeFavorited.toggleFavorite();
		
		FavoritesView.displayFavoriteResult(result, objectToBeFavorited);
		return result; //true if favorited, false if unfavorited
	}

}
