package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.FavoritesController;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

/**
 * The FavoritesView class handles receiving input from a user, as well as displaying results of operations
 * relating to favoriting a Media Product.
 * 
 * Has a field to store user input
 * Provides methods for viewing the results of favoriting a media product, getting information from the user, 
 * and displaying information summarizing favorites based on user input.
 * 
 * Accesses the media catalog from CatalogSingleton
 * Works closely with FavoritesController
 * @author etwil
 */
public class FavoritesView {
	
	static String userInput = "";
	
	/**
	 * Prompts user for a type input to base the favorites search on. 
	 * Calls gatherFavorited() from FavoritesController to collect favorited items based on 
	 * Calls displayFa
	 * @param ArrayList \<MediaProduct\> catalog
	 * @return String userInput 
	 * 
	 * @author etwil
	 */
	public String getFavInfo(ArrayList<MediaProduct> catalog) {
		Scanner scanner =  new Scanner(System.in);
        ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
        FavoritesController favoritesControllerO = new FavoritesController();
		
		//Continuously prompts user until a string matching the options is entered
		while (true) {
            System.out.println("What would you like to see your favorites summary for? songs, playlists, or all?");
            userInput = scanner.nextLine();

            userInput = userInput.trim().toLowerCase();
//Old code for when class Artist was in use           
//            if (userInput.equals("artists")) {
//            	favorites = favoritesControllerO.gatherFavorited(Artist.class, catalog);
//                break;
//            }
                if (userInput.equals("songs")) {
                	favorites = favoritesControllerO.gatherFavorited(Song.class, catalog);
                    break;
                }
                else if (userInput.equals("playlists")) {
                	favorites = favoritesControllerO.gatherFavorited(Playlist.class, catalog);
                    break;
                }
                else if (userInput.equals("all")) {
                	favorites = favoritesControllerO.gatherFavorited(MediaProduct.class, catalog);
                    break;
                }
                
             else  {
                System.out.println("Invalid input. Please try again.");
            }
        }

	   displayFavoritesInfo(favorites);
       return userInput;
    }
		
	
	/**
	 * Displays the result of the gatherFavorited operation, as well as messaging to the user
	 * Called by getFavInfo()
	 * @param ArrayList <? extends MediaProduct> favorites
	 * @return the list of favorites, or null in the case of an empty favorites list
	 * 
	 * @author etwil
	 */
	public ArrayList <? extends MediaProduct> displayFavoritesInfo(ArrayList <? extends MediaProduct> favorites){
		
		if (favorites.isEmpty()) {
			System.out.println("You have no favorites!");
			return null;
		}
		System.out.println("Favorites summary for " + userInput);
		System.out.println("You have " + favorites.size() + " favorites.");
		for(MediaProduct myObject : favorites) {
			System.out.println(myObject.getName());
		}
		
		return favorites;
	}
	
	
	
	
	
	
	
	/**
	 * Displays the result of the operation of favoriting or unfavoriting a media product object
	 * Used after an object to favorite is found 
	 * @param objectToBeFavorited
	 * @return an integer representing the result of the operation
	 * 		0 if the event was never found
	 * 		-1 if un-favoriting was successful
	 * 		1 if favoriting was successful
	 * 
	 * @author etwil
	 */
	public int displayFavoriteResult(MediaProduct objectToBeFavorited) {
		
		FavoritesController favoritescontroller = new FavoritesController();
		int favoriteResult = favoritescontroller.favorite(objectToBeFavorited);
		
		if (favoriteResult == 0) {
			System.out.println("Not found!");
			return 0;
		}
		else if(favoriteResult == -1) {
			 System.out.println("You un-favorited " + objectToBeFavorited.getName());
			 return -1;
		 }
		else {
			 System.out.println("You favorited " + objectToBeFavorited.getName());
			 return 1;
		 }
	}
	
}