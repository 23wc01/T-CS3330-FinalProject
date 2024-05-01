package edu.mu.finalproject.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CatalogSingleton {
    private static CatalogSingleton instance;
    private final List<MediaProduct> mediaProducts;


    private static final String SONGS_JSON_PATH = "files/songs.json";
    private static final String PLAYLISTS_JSON_PATH = "files/playlists.json";
    private static final String ARTISTS_JSON_PATH = "files/Artists.json";

    public CatalogSingleton() {
        mediaProducts = new ArrayList<>();
        loadMediaFromFiles();
    }

    private void loadMediaFromFiles() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(SONGS_JSON_PATH)));
            JSONArray songs = new JSONArray(content);
            for (int i = 0; i < songs.length(); i++) {
                JSONObject songObj = songs.getJSONObject(i);
                List<String> songNames = new ArrayList<>();
                JSONArray names = songObj.getJSONArray("songNames");
                for (int j = 0; j < names.length(); j++) {
                    songNames.add(names.getString(j));
                }
                // Assuming Song constructor takes these parameters
                Song song = new Song(songObj.getInt("id"), songObj.getString("name"),
                        songObj.getString("imgDescription"), songObj.getBoolean("isFavorited"),
                        new Date(), "Artist Name", Preference.POP);
                mediaProducts.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized CatalogSingleton getInstance() {
        if (instance == null) {
            instance = new CatalogSingleton();
        }
        return instance;
    }
// do we have search yet?
    public List<MediaProduct> searchMedia(String query) {
        List<MediaProduct> results = new ArrayList<>();
        for (MediaProduct media : mediaProducts) {
            if (media.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(media);
            }
        }
    return results;
    }

    public void addMediaProduct(MediaProduct mediaProduct) {
        mediaProducts.add(mediaProduct);
    }

    public void removeMediaProduct(int id) {
        mediaProducts.removeIf(m -> m.getId() == id);
    }

    public List<MediaProduct> getMediaProducts() {
        return new ArrayList<>(mediaProducts);
    }

    public void displayAll() {
        mediaProducts.forEach(System.out::println);
    }



}
