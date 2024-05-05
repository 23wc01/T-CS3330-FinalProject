package recommendPlaylistTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.RecommendPlaylistView;

class RecommendPlaylistViewTest {
	private static RecommendPlaylistView recommendPlaylistView;
	private static Preference preference;
	private static ArrayList<MediaProduct> mediaProducts;
	private static Playlist playlist;
	private static Song song;
	private static SimpleDateFormat dateFormat;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		recommendPlaylistView = new RecommendPlaylistView();
		preference = Preference.SOUL;
		playlist = new Playlist(0, "Soul songs", "Soul", new Date(), false, new ArrayList<Song>());	
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		mediaProducts = new ArrayList<MediaProduct>();
		song = new Song(10, "Isn't She Lovely", "love", false, (Date) dateFormat.parse("08-7-2018"), "Stevie Wonder", Preference.SOUL);
		mediaProducts.add(playlist);
		mediaProducts.add(song);
		playlist.getSongs().add(song);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDisplaySongRecommendations() {
		assertEquals(true, recommendPlaylistView.displaySongRecommendations(preference.capitalizePreference(), playlist.getSongs()));
		assertEquals(false, recommendPlaylistView.displaySongRecommendations(null, playlist.getSongs()));
		assertEquals(false, recommendPlaylistView.displaySongRecommendations(preference.capitalizePreference(), null));

	}

}
