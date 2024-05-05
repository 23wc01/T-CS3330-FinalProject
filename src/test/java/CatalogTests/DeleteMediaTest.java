package CatalogTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import edu.mu.finalproject.controller.MediaCatalogController;
import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Preference;

public class DeleteMediaTest {

    @Test
    public void testDeleteExistingMedia() {
        // Arrange - Add a media and test deleting it
        Song song = new Song(3, "Delete Song", "Description", true, new java.util.Date(), "Artist", Preference.EDM);
        CatalogSingleton.getInstance().getMediaProductCollection().add(song);

        // Act
        boolean result = MediaCatalogController.deleteMedia(song.getId());

        // Assert
        assertTrue("Media should be deleted successfully", result);
        assertTrue("Catalog should not contain the deleted media", CatalogSingleton.getInstance().getMediaProductCollection().isEmpty());
    }

    @Test
    public void testDeleteNonExistingMedia() {
        // Act
        boolean result = MediaCatalogController.deleteMedia(69); //id 69 is not exist
        // Assert
        assertFalse("Should return false as media does not exist", result);
    }
}
