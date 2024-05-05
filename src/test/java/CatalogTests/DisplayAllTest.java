package CatalogTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import edu.mu.finalproject.controller.MediaCatalogController;
import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Preference;

public class DisplayAllTest {

    @Test
    public void testDisplayAll() {
        // Arrange - Ensure there is at least one item in the catalog
        CatalogSingleton.getInstance().getMediaProductCollection().add(new Song(2, "Test Song", "Test Description", false, new java.util.Date(), "Test Artist", Preference.ROCK));

        // Act
        boolean result = MediaCatalogController.displayAll();

        // Assert
        assertTrue("Should return true if there are items to display", result);
    }

    @Test
    public void testDisplayAllEmpty() {
        // Arrange - Clear the catalog
        CatalogSingleton.getInstance().getMediaProductCollection().clear();

        // Act
        boolean result = MediaCatalogController.displayAll();

        // Assert
        assertFalse("Should return false if the catalog is empty", result);
    }
}
