package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;

public class FavoritesController {

	public static ArrayList<MediaProduct> gatherFavorited(Class<? extends MediaProduct> type) {
		
		ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
		
		for (MediaProduct media : catalog) { //REPLACE CATALOG
			if (media.getClass() == type) { //Use .getClass() not instanceOf because we don't want to count subclasses
				if (media.getIsFavorited()) {
					favorites.add(media);
				}
			}
		}
		
		return favorites;
	}
	
	
	
	
	
	public static boolean favorite(MediaProduct objectToBeFavorited) {
	
		boolean result = objectToBeFavorited.toggleFavorite();
		
		FavoritesView.displayFavoriteResult(result, objectToBeFavorited);
		return result; //true if favorited, false if unfavorited
	}

}
