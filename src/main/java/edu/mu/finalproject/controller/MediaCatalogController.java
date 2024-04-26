package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class MediaCatalogController {
    private static MediaCatalogController instance;
    private final List<MediaObject> media;
    private final Random random = new Random();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private MediaCatalogController() {
        media = new ArrayList<>();
    }

    public static synchronized MediaCatalogController getInstance() {
        if (instance == null) {
            instance = new MediaCatalogController();
        }
        return instance;
    }

    public void initializeCatalog() {
        // Placeholder for any initialization logic
    }

    public void loadMediaDatabase() {
        // Logic to load media items from a database or file system
    }

    public void addMedia(MediaObject mediaObject) {
        media.add(mediaObject);
    }

    public MediaObject createMedia(String type, Map<String, String> attributes) {
        if ("song".equalsIgnoreCase(type)) {
            try {
                int id = Integer.parseInt(attributes.get("id"));
                String name = attributes.get("name");
                String description = attributes.get("description");
                Date addedDate = dateFormatter.parse(attributes.get("addedDate"));
                Boolean isFavorited = Boolean.parseBoolean(attributes.get("isFavorited"));
                float rating = Float.parseFloat(attributes.get("rating"));
                int artistId = Integer.parseInt(attributes.get("artistId"));
                Preference preference = Preference.valueOf(attributes.get("preference").toUpperCase());
                return new Song(id, name, description, addedDate, isFavorited, rating, artistId, preference);
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }
        // ...other media types...
        return null;
    }

    public void deleteMedia(String id) {
        media.removeIf(m -> Integer.toString(m.getId()).equals(id));
    }

    public void deleteMediaByType(String type, String name) {
        media.removeIf(m -> m.getClass().getSimpleName().equalsIgnoreCase(type) && m.getName().equalsIgnoreCase(name));
    }

    public void displayAll() {
        media.forEach(System.out::println);
    }

    public void displayByType(String type) {
        media.stream()
                .filter(m -> m.getClass().getSimpleName().equalsIgnoreCase(type))
                .forEach(System.out::println);
    }

    public void displayShuffle() {
        Collections.shuffle(media, random);
        displayAll();
    }

    // These methods will need to be filled in with logic to read from a database or file
//    public Playlist createPlaylistFromDatabase(String filePath) {
//        // Implement logic to read playlist data from a file and create a playlist object
//        return new Playlist(); // Placeholder return
//    }
//
//    public Playlist parsePlaylistJson(String json) {
//        // Implement logic to parse JSON into a playlist
//        return new Playlist(); // Placeholder return
//    }
}