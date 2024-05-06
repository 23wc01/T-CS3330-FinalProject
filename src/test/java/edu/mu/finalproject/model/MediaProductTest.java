package edu.mu.finalproject.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

class MediaProductTest {
	private static Song song1;
	private static Song song2;
	private static Song song3;
	private static Song song4;
	private static Playlist playlist;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		song1 = new Song(0, "test", null, null, null, null, null);
		song2 = new Song(0, "test", null, null, null, null, null);
		song3 = new Song(0, "different", null, null, null, null, null);
		song4 = new Song(1, "test", null, null, null, null, null);
		playlist = new Playlist(0, "test", null, null, null, null);
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
	void testCompareTo() {
		assertTrue(song1.equals(song2));
		assertFalse(song1.equals(song3));
		assertFalse(song1.equals(song4));

		assertFalse(song1.equals(playlist));
		assertFalse(song1.equals(null));	
	}
	@Test
	void testGetId() {
		assertEquals(0, song1.getId());
	}
}
