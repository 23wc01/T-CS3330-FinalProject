package CatalogTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.controller.MediaCatalogController;

public class AddMediaTest {

    @Test
    public void testAddMedia() {
        // Arrange
        Song newSong = new Song(1, "New Song", "Description", true, new java.util.Date(), "New Artist", Preference.POP);
        int initialSize = CatalogSingleton.getInstance().getMediaProductCollection().size();

        // Act
        boolean result = MediaCatalogController.addMedia(newSong);

        // Assert
        assertTrue("Media should be added successfully", result);
        assertEquals("Catalog size should increase by 1", initialSize + 1, CatalogSingleton.getInstance().getMediaProductCollection().size());
    }
}
