package searchTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.controller.SearchController;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Preference;

class SearchControllerTest {
	private static SearchController searchController;
	private static SimpleDateFormat dateFormat;
	private static ArrayList<MediaProduct> mediaProducts;
	private static Playlist playlist;
	private static Song song;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		mediaProducts = new ArrayList<MediaProduct>();
		playlist = new Playlist(0, "Peanut Butter Jelly Time", null, new Date(), null, null);
		song = new Song(6, "Peanut Butter Jelly Time", "Peanut butter", false, (Date) dateFormat.parse("07-20-2020"), "Dj Chipman", Preference.RAP);
		mediaProducts.add(playlist);
		mediaProducts.add(song);
	}

	@BeforeEach
	void setUp() throws Exception {
		searchController = new SearchController();
	}

	
	/**
	 * Success - SearchController.searchSongName("Peanut Butter Jelly Time", mediaProducts);
	 * Fail - SearchController.searchSongName(null, null);
	 * Fail - SearchController.searchSongName(null, mediaProduct);
	 * Fail - SearchController.searchSongName("", mediaProducts);
	 * Fail - SearchController.searchSongName("Non-avaliable song", mediaProducts);
	 */
	@Test
	void testSearchSongName() {
		assertEquals(song, SearchController.searchSongName("Peanut Butter Jelly Time", mediaProducts));
		assertNull(SearchController.searchSongName(null, null));
		assertNull(SearchController.searchSongName(null, mediaProducts));
		assertNull(SearchController.searchSongName("", mediaProducts));
		assertNull(SearchController.searchSongName("Non-avaliable song", mediaProducts));
	}
	@Test
	void testGetTopNResults() {
		// Assert
		assertEquals(5, SearchController.getTopNResults());		
	}
	@Test
	void testSetTopNResults() {
		// Assert
		assertTrue(SearchController.setTopNResults(1));
		assertFalse(SearchController.setTopNResults(0));
		assertFalse(SearchController.setTopNResults(-1));
	}
	@Test
	void testSearchSort() {
		assertTrue(searchController.searchSort("Peanut"));
		assertFalse(searchController.searchSort(""));
	}
}
