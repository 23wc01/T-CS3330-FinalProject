package EventTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.mu.finalproject.controller.FavoritesController;
import edu.mu.finalproject.model.Artist;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.FavoritesView;

public class FavoriteTest {
	
	@Test
	public void testFavoriteSong() {
		//Arrange
		Song testSongFavorite = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(), false, "icanrockyourworld", Preference.EDM);
		Song testSongUnfavorite = new Song(3, "Hot potato", "potato", new Date(), true, "Patty Shukla", Preference.POP);
		FavoritesController favoritesController = new FavoritesController();
		
		//Act
		boolean testResult1 = favoritesController.favorite(testSongFavorite);
		boolean testResult2 = favoritesController.favorite(testSongUnfavorite);
		
		//Assert
		assertTrue(testResult1);
		assertFalse(testResult2);
	}
	
	@Test
	public void testFavoritePlaylist() {
		//Arrange
		//Songs for playlists
		Song testSongFavorite = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(), false, "icanrockyourworld", Preference.EDM);
		Song testSongUnfavorite = new Song(3, "Hot potato", "potato", new Date(), true, "Patty Shukla", Preference.POP);
		
		ArrayList<Song> playlist1 = new ArrayList<>();
		ArrayList<Song> playlist2 = new ArrayList<>();
		
		playlist1.add(testSongFavorite);
		playlist2.add(testSongUnfavorite);
		
		Playlist testPlaylistFavorite = new Playlist(0, "Playlist one", "chill", new Date(), false, playlist1);
		Playlist testPlaylistUnfavorite = new Playlist(3, "Playlist two", "hype", new Date(), true, playlist2);
		FavoritesController favoritesController = new FavoritesController();
		
		//Act
		boolean testResult1 = favoritesController.favorite(testPlaylistFavorite);
		boolean testResult2 = favoritesController.favorite(testPlaylistUnfavorite);
		
		//Assert
		assertTrue(testResult1);
		assertFalse(testResult2);
	}
	
	@Test
	public void testFavoriteArtist() {
		//Arrange
		Artist testArtistFavorite = new Artist(1, "jack", "bad", new Date(), false, (float) 12.2, 3);
		Artist testArtistUnfavorite = new Artist(3, "max", "good", new Date(), true, (float) 12.3, 2);
		FavoritesController favoritesController = new FavoritesController();
		
		//Act
		boolean testResult1 = favoritesController.favorite(testArtistFavorite);
		boolean testResult2 = favoritesController.favorite(testArtistUnfavorite);
		
		//Assert
		assertTrue(testResult1);
		assertFalse(testResult2);
	}
	
	@Test
	public void testToggleFavorite() {
		//Arrange
		Song testSongFavorite = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(), false, "icanrockyourworld", Preference.EDM);
		Song testSongUnfavorite = new Song(3, "Hot potato", "potato", new Date(), true, "Patty Shukla", Preference.POP);
		
		//Act
		boolean testResult1 = testSongFavorite.toggleFavorite();
		boolean testResult2 = testSongUnfavorite.toggleFavorite();
		
		//Assert
		assertTrue(testResult1);
		assertFalse(testResult2);
	}
	
	
	@Test
	public void testDisplayFavoriteResult() {
		//Arrange
		Song testSongFavorite = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(), false, "icanrockyourworld", Preference.EDM);
		Song testSongUnfavorite = new Song(3, "Hot potato", "potato", new Date(), true, "Patty Shukla", Preference.POP);
		FavoritesView favoritesview = new FavoritesView();
		
		//Act
		boolean testResult1 = favoritesview.displayFavoriteResult(true, testSongUnfavorite);
		boolean testResult2 = favoritesview.displayFavoriteResult(false, testSongFavorite);
		
		//Assert
		assertTrue(testResult1);
		assertFalse(testResult2);
		
	}
	

}
