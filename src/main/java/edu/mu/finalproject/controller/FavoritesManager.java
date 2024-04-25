package edu.mu.finalproject.controller;




import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.util.FindObject;

public class FavoritesManager {
	
	private static FavoritesManager instance;
	
	//private constructor to prevent instantiation
	private FavoritesManager() {
	}
	
	//Static method to provide access to the single instance
	 public static FavoritesManager getInstance() {
	 
	 	//create instance if it doesn't exist
	 	if (instance == null){
	 		instance = new FavoritesManager();
	 	}
	 	
	 	return instance;
	 }
	 
	 public void favorite() {
		                                                                //!!!
		 MediaObject objectToBeFavorited = FindObject.searchMediaCatalog(null); //!!! Add real catalog here!
		                                                                //!!!
		 int result = objectToBeFavorited.toggleFavorite();
		 
		 if(result < 0) {
			 System.out.println("You already favorited that!");
		 }
		 else {
			 objectToBeFavorited.setIsFavorited(true);
			 System.out.println("You favorited " + objectToBeFavorited.getName());
		 }
		 
	 }
	 
	 

}
