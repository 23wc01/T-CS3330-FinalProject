package downloadPlaylistTests;


import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.util.downloadPlaylistBuilder.HtmlDirector;

class HtmlDirectorTest {
	private static HtmlDirector htmlDirector;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		htmlDirector = new HtmlDirector();
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
}
