package edu.mu.finalproject.model;




import edu.mu.finalproject.view.FindObjectView;

public class FavoritesSingleton {
	
	private static FavoritesSingleton instance;
	
	//private constructor to prevent instantiation
	private FavoritesSingleton() {
	}
	
	//Static method to provide access to the single instance
	 public static FavoritesSingleton getInstance() {
	 
	 	//create instance if it doesn't exist
	 	if (instance == null){
	 		instance = new FavoritesSingleton();
	 	}
	 	
	 	return instance;
	 }
	 
	 public void favorite() {
		                                                                //!!!
		 MediaObject objectToBeFavorited = FindObjectView.searchMediaCatalog(null); //!!! Add real catalog here!
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
