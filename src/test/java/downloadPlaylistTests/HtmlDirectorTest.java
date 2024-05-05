package downloadPlaylistTests;


import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.downloadPlaylistBuilder.HtmlDirector;

class HtmlDirectorTest {
	private static HtmlDirector htmlDirector;
	private static Playlist playlist;
	private static Account account;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		htmlDirector = new HtmlDirector();
		playlist = new Playlist(0, "test", "test", new Date(), false, new ArrayList<Song>());
		account = new Account(0, "test", "test");
		ArrayList<Boolean> stepsSuccess = new ArrayList<Boolean>();
		stepsSuccess.add(false);
		htmlDirector.setConstructionSuccess(stepsSuccess);
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
	void testConstructPlaylistHtml() {
		assertNull(htmlDirector.constructPlaylistHtml(null));
	}

	@Test
	void testConstructSongHtml() {
		assertNull(htmlDirector.constructSongHtml(null));
	}
	
	@Test
	void testConstructAccountHtml() {
		assertNull(htmlDirector.constructAccountHtml(null));
	}
	
	@Test 
	void testGetconstructionSuccess() {
		htmlDirector.setConstructionSuccess(null);
		assertNull(htmlDirector.getConstructionSuccess());
	}
}
