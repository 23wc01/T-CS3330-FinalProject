package EventTests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import edu.mu.finalproject.controller.FindObjectController;
import edu.mu.finalproject.model.Artist;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;

public class FindObjectTest {
	
	private ArrayList<MediaProduct> catalog;
	
	@Test
	public void testSearchMediaCatalog_Success() {
		//Arrange
		FindObjectController findobjectcontroller = new FindObjectController();
		//Set up fake catalog
    	catalog = new ArrayList<>();
    	
    	  //Fake playlist
		Song testSongforPlaylist = new Song(3, "Hot potato", "potato", new Date(0), true, "Patty Shukla", Preference.POP);
		ArrayList<Song> testPlaylistSongArray = new ArrayList<>();
		testPlaylistSongArray.add(testSongforPlaylist);
    	
		  //Test media products
    	Song testSong = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM);
    	Artist testArtist = new Artist(1, "jack", "bad", new Date(0), true, (float) 12.2);
    	Playlist testPlaylist = new Playlist(0, "Playlist one", "chill", new Date(0), false, testPlaylistSongArray);
    	  //Add to fake catalog
        catalog.add(testSong);
        catalog.add(testPlaylist);
        catalog.add(testArtist);
		
		//Act
        MediaProduct testProduct = findobjectcontroller.searchMediaCatalog(catalog, "The Gummy Bear Song");
		
		//Assert
        assertEquals(testSong, testProduct);
	}
	
	
	
	@Test
	public void testSearchMediaCatalog_Null() {
		//Arrange
		FindObjectController findobjectcontroller = new FindObjectController();
		//Set up fake catalog
    	catalog = new ArrayList<>();
    	
    	  //Fake playlist
		Song testSongforPlaylist = new Song(3, "Hot potato", "potato", new Date(0), true, "Patty Shukla", Preference.POP);
		ArrayList<Song> testPlaylistSongArray = new ArrayList<>();
		testPlaylistSongArray.add(testSongforPlaylist);
    	
		  //Test media products
    	Song testSong = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM);
    	Artist testArtist = new Artist(1, "jack", "bad", new Date(0), true, (float) 12.2);
    	Playlist testPlaylist = new Playlist(0, "Playlist one", "chill", new Date(0), false, testPlaylistSongArray);
    	  //Add to fake catalog
        catalog.add(testSong);
        catalog.add(testPlaylist);
        catalog.add(testArtist); 
        
		
		//Act
		MediaProduct testProduct = findobjectcontroller.searchMediaCatalog(catalog, null);
		
		//Assert
		assertEquals(null, testProduct);
	}
	
	
	
	@Test
	public void testSearchMediaCatalog_NotFound() {
		//Arrange
		FindObjectController findobjectcontroller = new FindObjectController();
		//Set up fake catalog
    	catalog = new ArrayList<>();
    	
    	  //Fake playlist
		Song testSongforPlaylist = new Song(3, "Hot potato", "potato", new Date(0), true, "Patty Shukla", Preference.POP);
		ArrayList<Song> testPlaylistSongArray = new ArrayList<>();
		testPlaylistSongArray.add(testSongforPlaylist);
    	
		  //Test media products
    	Song testSong = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM);
    	Artist testArtist = new Artist(1, "jack", "bad", new Date(0), true, (float) 12.2);
    	Playlist testPlaylist = new Playlist(0, "Playlist one", "chill", new Date(0), false, testPlaylistSongArray);
    	  //Add to fake catalog
        catalog.add(testSong);
        catalog.add(testPlaylist);
        catalog.add(testArtist); 
        
		
		//Act
		MediaProduct testProduct = findobjectcontroller.searchMediaCatalog(catalog, "Not a song");
		
		//Assert
		assertEquals(null, testProduct);
	}
	

}
