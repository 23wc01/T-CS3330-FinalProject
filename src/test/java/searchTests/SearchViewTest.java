package searchTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.SearchView;

class SearchViewTest {
	private static SearchView searchView;
	private static SimpleDateFormat dateFormat;
	private static ArrayList<MediaProduct> mediaProducts;
	private static Playlist playlist;
	private static Song song;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		searchView = new SearchView();
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		mediaProducts = new ArrayList<MediaProduct>();
		playlist = new Playlist(0, "Peanut Butter Jelly Time", null, new Date(), null, null);
		song = new Song(6, "Peanut Butter Jelly Time", "Peanut butter", false, (Date) dateFormat.parse("07-20-2020"),
				"Dj Chipman", Preference.RAP);
		mediaProducts.add(playlist);
		mediaProducts.add(song);
	
	}

	@Test
	void testDisplaySearchResultsView() {
		assertTrue(searchView.displaySearchResults("Peanut", 1, mediaProducts));
		assertFalse(searchView.displaySearchResults(null, 1, mediaProducts));
		assertFalse(searchView.displaySearchResults("Peanut", 0, mediaProducts));
		assertFalse(searchView.displaySearchResults("Peanut", 1, null));
	}
	
	@Test
    void testScanQuery() {
		Scanner successScanner = new Scanner("test search");         
		assertEquals("test search", searchView.getSearchQuery(successScanner));
		assertNull(searchView.getSearchQuery(null));
	}
}
