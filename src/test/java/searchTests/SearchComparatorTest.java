package searchTests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.SearchComparator;

class SearchComparatorTest {
	private static SearchComparator searchComparator;
	private static SimpleDateFormat dateFormat;
	private static ArrayList<MediaProduct> mediaProducts;
	private static Playlist playlist;
	private static Song song;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		mediaProducts = new ArrayList<MediaProduct>();
		searchComparator = new SearchComparator("Peanut Butter Jelly Time");
		playlist = new Playlist(0, "Peanut Butter Jelly Time", null, new Date(), null, null);
		song = new Song(6, "Peanut Butter Jelly Time", "Peanut butter", false, (Date) dateFormat.parse("07-20-2020"), "Dj Chipman", Preference.RAP);
		mediaProducts.add(playlist);
		mediaProducts.add(song);
	}


	@Test
	void testCompare() {
		assertEquals(0, searchComparator.compare(song, playlist));
		assertEquals(-1, searchComparator.compare(null, playlist));
	}

}
