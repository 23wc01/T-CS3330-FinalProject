package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.FavoritesController;
import edu.mu.finalproject.model.Artist;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

public class FavoritesView {
	
	static String userInput = "";
	
	
	public String getFavInfo(ArrayList<MediaProduct> catalog) {
		Scanner scanner =  new Scanner(System.in);
        ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
        FavoritesController favoritesControllerO = new FavoritesController();
		
		//Continuously prompts user until a string matching the options is entered
		while (true) {
            System.out.println("What would you like to see your favorites summary for? artists, songs, playlists, or all?");
            userInput = scanner.nextLine();

            userInput = userInput.trim().toLowerCase();
            
            if (userInput.equals("artists")) {
            	favorites = favoritesControllerO.gatherFavorited(Artist.class, catalog);
                break;
            }
                else if (userInput.equals("songs")) {
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
		
	

	public ArrayList <? extends MediaProduct> displayFavoritesInfo(ArrayList <? extends MediaProduct> favorites){
		
		if (favorites == null) {
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
	
	
	
	
	
	
	
	
	public boolean displayFavoriteResult(boolean favoriteResult, MediaProduct objectToBeFavorited) {
		
		if(favoriteResult == false) {
			 System.out.println("You un-favorited " + objectToBeFavorited.getName());
			 return false;
		 }
		 else {
			 System.out.println("You favorited " + objectToBeFavorited.getName());
			 return true;
		 }
	}
	
}
