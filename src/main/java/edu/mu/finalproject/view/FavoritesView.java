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
	
	public static void getFavInfo() {
		Scanner scanner =  new Scanner(System.in);
		
		//Continuously prompts user until a string matching the options is entered
		while (true) {
            System.out.println("What would you like to see your favorites summary for? artists, songs, playlists, or all?");
            userInput = scanner.nextLine();

            userInput = userInput.trim().toLowerCase();
            
            if (userInput.equals("artists")) {
                displayFavoritesInfo(Artist.class);
                break;
            }
                else if (userInput.equals("songs")) {
                	displayFavoritesInfo(Song.class);
                    break;
                }
                else if (userInput.equals("playlists")) {
                	displayFavoritesInfo(Playlist.class);
                    break;
                }
                else if (userInput.equals("all")) {
                	displayFavoritesInfo(MediaProduct.class);
                    break;
                }
                
             else  {
                System.out.println("Invalid input. Please try again.");
            }
        }

       
    }
		
	

	public static void displayFavoritesInfo(Class <? extends MediaProduct> type){
		ArrayList <MediaProduct> favorites = new ArrayList<MediaProduct>();
		favorites = FavoritesController.gatherFavorited(type);
		
		System.out.println("Favorites summary for " + userInput.toLowerCase());
		System.out.println("You have " + favorites.size() + " favorites.");
		for(MediaProduct myObject : favorites) {
			System.out.println(myObject.getName());
		}
	}
	
}
