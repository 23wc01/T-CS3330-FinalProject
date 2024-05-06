package CatalogTests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;
import edu.mu.finalproject.model.CatalogFileReader;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Preference;

public class CatalogFileReaderTest {

    private static final String FILE_PATH = "path/to/catalog.json";

    @Test
    public void testReadCatalog() {
        // Arrange
        ArrayList<Song> expected = new ArrayList<>();
        expected.add(new Song(3, "Pretty Savage", "Description", true, new java.util.Date(), "Artist", Preference.POP));

        // Act
        ArrayList<Song> actual = MediaFileReader.readCatalog(FILE_PATH);

        // Assert
        assertEquals("Should match the number of songs read", expected.size(), actual.size());
    }
}
