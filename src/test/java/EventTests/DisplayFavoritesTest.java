package EventTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import EventTests.DisplayFavoritesTest.MockMediaProduct;
import edu.mu.finalproject.controller.FavoritesController;
import edu.mu.finalproject.model.Artist;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.FavoritesView;



public class DisplayFavoritesTest {
	
	
	
    // Mock class for MediaProduct
    static class MockMediaProduct extends MediaProduct {
        private String name;

        public MockMediaProduct(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }



    private ArrayList<MediaProduct> favorites;
    private ArrayList<MediaProduct> catalog;
    FavoritesView tFavoritesView = new FavoritesView();
    FavoritesController tFavoritesController = new FavoritesController();



    @Test
    public void testDisplayFavoritesInfo() {
    	favorites = new ArrayList<>();
        favorites.add(new MockMediaProduct("Product 1"));
        favorites.add(new MockMediaProduct("Product 2"));

        assertEquals(favorites, tFavoritesView.displayFavoritesInfo(favorites));
    }
    
    @Test
    public void testDisplayFavoritesInfo_NoEvents() {
    	//Arrange
    	favorites = new ArrayList<>();
    	
    	//Act/Assert
    	assertEquals(null, tFavoritesView.displayFavoritesInfo(favorites));
    }

    
    @Test
    public void testGatherFavorited_All() {  //Doesn't work, not quite matching at the very end
    	//Arrange
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

  
          //Set up expected catalog to test against
        ArrayList<MediaProduct> expected = new ArrayList<>(); 
        expected.add(new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM));
        expected.add(new Artist(1, "jack", "bad", new Date(0), true, (float) 12.2));
        //could uncomment to test with playlist favorited
        //expected.add(new Playlist(0, "Playlist one", "chill", new Date(0), true, testPlaylistSongArray));
        
        //Act
        ArrayList <MediaProduct> result = tFavoritesController.gatherFavorited(MediaProduct.class, catalog);
        
        //Assert
        assertEquals(expected, result);
    }
    
    
    
    @Test
    public void testGatherFavorited_OneType() {  //Doesn't work, not quite matching at the very end
    	System.out.println("-----------------------------");
    	//Arrange
    	  //Set up fake catalog
    	catalog = new ArrayList<>();
    	
    	
		  //Test songs 
    	Song song0 = new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM);
		Song song1 = new Song(1, "Peanut Butter Jelly Time", "peanut",new Date(0), true, "Kids Tunez", Preference.RAP);
		Song song2 = new Song(2, "The Guacamole Song", "avocado", new Date(0), false, "Dr. Jean", Preference.POP);
    	
    	  //Add to fake catalog
        catalog.add(song0);
        catalog.add(song1);
        catalog.add(song2);

  
          //Set up expected catalog to test against
        ArrayList<MediaProduct> expected = new ArrayList<>(); 
        expected.add(new Song(0, "The Gummy Bear Song", "gummy bear", new Date(0), true, "icanrockyourworld", Preference.EDM));
        expected.add(new Song(1, "Peanut Butter Jelly Time", "peanut",new Date(0), true, "Kids Tunez", Preference.RAP));
        
        //Act
        ArrayList <MediaProduct> result = tFavoritesController.gatherFavorited(Song.class, catalog);
        
        //Assert
        assertEquals(expected, result);
    }
    

    
    
    
    
    

    @Test
    public void testGetFavInfo_Artists() {
        // Arrange
        String simulatedInput = "artists"; // Simulating user input for "artists"
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        
     // Mock catalog
        ArrayList<MediaProduct> testCatalog = new ArrayList<>();
     // Mocking the FavoritesController.gatherFavorited method
        FavoritesController favoritesControllerMock = mock(FavoritesController.class);
        ArrayList<MediaProduct> mockResult = new ArrayList<>();
        when(favoritesControllerMock.gatherFavorited(Artist.class, testCatalog)).thenReturn(mockResult);

        // Act
        String result = tFavoritesView.getFavInfo(testCatalog);

        // Assert
        assertEquals("artists", result);

    }
    
    @Test
    public void testGetFavInfo_Songs() {
        // Arrange
        String simulatedInput = "songs"; // Simulating user input for "artists" 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        
     // Mock catalog
        ArrayList<MediaProduct> testCatalog = new ArrayList<>();
     // Mocking the FavoritesController.gatherFavorited method
        FavoritesController favoritesControllerMock = mock(FavoritesController.class);
        ArrayList<MediaProduct> mockResult = new ArrayList<>();
        when(favoritesControllerMock.gatherFavorited(Song.class, testCatalog)).thenReturn(mockResult);

        // Act
        String result = tFavoritesView.getFavInfo(testCatalog);

        // Assert
        assertEquals("songs", result);

    }
    
    @Test
    public void testGetFavInfo_Playlists() {
        // Arrange
        String simulatedInput = "playlists"; // Simulating user input for "artists"
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        
     // Mock catalog
        ArrayList<MediaProduct> testCatalog = new ArrayList<>();
     // Mocking the FavoritesController.gatherFavorited method
        FavoritesController favoritesControllerMock = mock(FavoritesController.class);
        ArrayList<MediaProduct> mockResult = new ArrayList<>();
        when(favoritesControllerMock.gatherFavorited(Playlist.class, testCatalog)).thenReturn(mockResult);

        // Act
        String result = tFavoritesView.getFavInfo(testCatalog);

        // Assert
        assertEquals("playlists", result);

    }
    
    @Test
    public void testGetFavInfo_All() {
        // Arrange
        String simulatedInput = "all"; // Simulating user input for "artists"
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        
     // Mock catalog
        ArrayList<MediaProduct> testCatalog = new ArrayList<>();
     // Mocking the FavoritesController.gatherFavorited method
        FavoritesController favoritesControllerMock = mock(FavoritesController.class);
        ArrayList<MediaProduct> mockResult = new ArrayList<>();
        when(favoritesControllerMock.gatherFavorited(MediaProduct.class, testCatalog)).thenReturn(mockResult);

        // Act
        String result = tFavoritesView.getFavInfo(testCatalog);

        // Assert
        assertEquals("all", result);

    }
    
    @After
    public void tearDown() {
        System.setIn(System.in); // Reset System.in to its original
       
    }

}
