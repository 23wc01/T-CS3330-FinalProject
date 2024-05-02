package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;

import edu.mu.finalproject.view.FavoritesView;
import edu.mu.finalproject.view.FindObjectView;
/**
 * The FavoritesController class handles logic for favoriting media products and gathering favorited products
 * 
 * It works closely with FavoritesView, MediaProduct, Song, Playlist, and Artist classes. 
 * It uses the media product catalog from CatalogSingleton
 * 
 * @author etwil
 */
public class FavoritesController {

	/**
	 * Checks if catalog is null or empty, then iterates through catalog and checks if object is favorited
	 * If favorited, it is added to array favorites to be returned.
	 * @param Class<? extends MediaProduct> type - class of media product
	 * @param ArrayList catalog - the catalog of media
	 * @return ArrayList favorites - the favorited items
	 * @author etwil
	 */
	public ArrayList<MediaProduct> gatherFavorited(Class<? extends MediaProduct> type, ArrayList<MediaProduct> catalog) {
		
		ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
		//For an empty or null list
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
	
	/**
	 * 
	 * @param MediaProduct objectToBeFavorited 
	 * @return an int representing if the product was favorited or unfavorited
	 * 		1 for object favorited
	 * 	   -1 for object unfavorited
	 *      0 for null object parameter 
	 * @author etwil
	 */
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
