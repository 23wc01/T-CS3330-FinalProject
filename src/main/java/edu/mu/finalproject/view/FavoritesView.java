package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.controller.FavoritesController;
import edu.mu.finalproject.model.Artist;
import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

public class FavoritesView {
	
	static String userInput = "";
	
	public static String getFavInfo() {
		Scanner scanner =  new Scanner(System.in);
        ArrayList <MediaObject> favorites = new ArrayList<MediaObject>();
		
		//Continuously prompts user until a string matching the options is entered
		while (true) {
            System.out.println("What would you like to see your favorites summary for? artists, songs, playlists, or all?");
            userInput = scanner.nextLine();

            userInput = userInput.trim().toLowerCase();
            
            if (userInput.equals("artists")) {
            	favorites = FavoritesController.gatherFavorited(Artist.class);
                break;
            }
                else if (userInput.equals("songs")) {
                	favorites = FavoritesController.gatherFavorited(Song.class);
                    break;
                }
                else if (userInput.equals("playlists")) {
                	favorites = FavoritesController.gatherFavorited(Playlist.class);
                    break;
                }
                else if (userInput.equals("all")) {
                	favorites = FavoritesController.gatherFavorited(MediaObject.class);
                    break;
                }
                
             else  {
                System.out.println("Invalid input. Please try again.");
            }
        }

	   displayFavoritesInfo(favorites);
       return userInput;
    }
		
	

	public static ArrayList <? extends MediaObject> displayFavoritesInfo(ArrayList <? extends MediaObject> favorites){
		
		System.out.println("Favorites summary for " + userInput);
		System.out.println("You have " + favorites.size() + " favorites.");
		for(MediaObject myObject : favorites) {
			System.out.println(myObject.getName());
		}
		
		return favorites;
	}
	
	
	
	
	
	
	
	
	public static boolean displayFavoriteResult(boolean favoriteResult, MediaObject objectToBeFavorited) {
		
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
