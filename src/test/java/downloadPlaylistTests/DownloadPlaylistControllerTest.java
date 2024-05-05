package downloadPlaylistTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.controller.DownloadPlaylistController;
import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;

class DownloadPlaylistControllerTest {
	private static DownloadPlaylistController downloadPlaylistController;
	private static Account account;
	private static ArrayList<MediaProduct> mediaProducts;
	private static Playlist playlist;
	private static Song song;
	private static SimpleDateFormat dateFormat;
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		downloadPlaylistController = new DownloadPlaylistController();
		account = new Account(0, "test", "test");
		account.setUserPreference(Preference.SOUL);
		
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		mediaProducts = new ArrayList<MediaProduct>();
		song = new Song(10, "Isn't She Lovely", "love", false, (Date) dateFormat.parse("08-7-2018"), "Stevie Wonder", Preference.SOUL);
		mediaProducts.add(playlist);
		mediaProducts.add(song);
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
	void testDownloadRecommendedPlaylist() {
		assertTrue(downloadPlaylistController.downloadRecommendedPlaylist(account, mediaProducts));
		assertFalse(downloadPlaylistController.downloadRecommendedPlaylist(null, mediaProducts));
		assertFalse(downloadPlaylistController.downloadRecommendedPlaylist(account, null));
	}
	@Test 
	void testCreatePlaylist() {
		assertTrue(downloadPlaylistController.createPlaylist(account, mediaProducts));
		assertFalse(downloadPlaylistController.createPlaylist(account, null));
		assertFalse(downloadPlaylistController.createPlaylist(null, null));

	}
}
