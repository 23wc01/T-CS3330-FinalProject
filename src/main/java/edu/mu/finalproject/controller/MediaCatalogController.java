package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

import java.util.*;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.json.JSONObject;
import org.json.JSONArray;

public class MediaCatalogController {
    private static final Logger LOGGER = Logger.getLogger(MediaCatalogController.class.getName());
    private final CatalogSingleton catalogSingleton;
    private final Random random = new Random();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");

    public MediaCatalogController() {
        catalogSingleton = CatalogSingleton.getInstance();
    }

    public void addMedia(MediaProduct mediaProduct) {
        catalogSingleton.addMediaProduct(mediaProduct);
    }

    public void deleteMedia(int id) {
        catalogSingleton.removeMediaProduct(id);
    }

    public void displayAll() {
        catalogSingleton.displayAll();
    }
    void displayShuffle() {
        List<MediaProduct> shuffledMedia = new ArrayList<>(catalogSingleton.getMediaProducts());
        Collections.shuffle(shuffledMedia, random);
        shuffledMedia.forEach(System.out::println);
    }

    // Load playlists from a JSON file and add them to the catalog
    public void loadPlaylistsFromFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray playlists = jsonObject.getJSONArray("playlists");
            SearchController searchController = new SearchController();
            for (int i = 0; i < playlists.length(); i++) {
                JSONObject playlistJson = playlists.getJSONObject(i);
                ArrayList<Song> songs = new ArrayList<Song>();
                JSONArray songNames = playlistJson.getJSONArray("songNames");
                for (int j = 0; j < songNames.length(); j++) {
                    String songName = songNames.getString(j);
                    // I need help to get search method
                    Song song = catalogSingleton.searchMedia(songName);
                    if (song != null) {
                        songs.add(song);
                    }
                }
                Playlist playlist = new Playlist(
                        playlistJson.getInt("id"),
                        playlistJson.getString("name"),
                        playlistJson.getString("imgDescription"),
                        new Date(),
                        playlistJson.getBoolean("isFavorited"),
                        songs);
                catalogSingleton.addMediaProduct(playlist);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error reading playlist from file: " + filePath, e);
        }
    }
    public void findAndDisplaySongs(String songName) {
        List<MediaProduct> results = catalogSingleton.searchMedia(songName);
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        } else {
            System.out.println("No songs found matching: " + songName);
        }
    }
}
