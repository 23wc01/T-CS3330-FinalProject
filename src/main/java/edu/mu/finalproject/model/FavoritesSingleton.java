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
	
	 
	 

}
