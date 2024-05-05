package downloadPlaylistTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.view.DownloadPlaylistView;
class DownloadPlaylistViewTest {
	private static DownloadPlaylistView downloadPlaylistView;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		downloadPlaylistView = new DownloadPlaylistView();
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
	void testDisplayDownloadMessage() {
		assertTrue(downloadPlaylistView.displayDownloadMessage("test/filepath", true));
		assertFalse(downloadPlaylistView.displayDownloadMessage("test/filepath", false));
		assertFalse(downloadPlaylistView.displayDownloadMessage(null, false));
	}

}
