package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;

import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;

public class FavoritesController {

	public ArrayList<MediaProduct> gatherFavorited(Class<? extends MediaProduct> type, ArrayList<MediaProduct> catalog) {
		
		ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
		if (catalog.isEmpty() || catalog == null ) {
			return favorites;
		}
		
		//To get all favorited
		if (type == MediaProduct.class) {
			for (MediaProduct media : catalog) {
				if (media.getIsFavorited()) {
					favorites.add(media);
				}
			}
		}
		
		//To get certain types of favorited
		else {
			for (MediaProduct media : catalog) { 
				if (media.getClass() == type) { //Use .getClass() not instanceOf because we don't want to count subclasses
					if (media.getIsFavorited()) {
						favorites.add(media);
					}
				}
			}
		}
		return favorites;
	}
	
	
	public int favorite(MediaProduct objectToBeFavorited) {
		
		if (objectToBeFavorited == null) {
			return 0;
		}
	
		boolean result = objectToBeFavorited.toggleFavorite();

		if (result == true) {
			return 1;
		}
		else {
			return -1;
		}
	}


}
