package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.Catalog;
import edu.mu.finalproject.model.MediaProduct;
//import edu.mu.finalproject.model.Song;
//import edu.mu.finalproject.model.Preference;
import java.util.*;
import java.text.SimpleDateFormat;
//import java.text.ParseException;
import java.util.logging.Logger;
//import java.util.logging.Level;

public class MediaCatalogController {
    private static final Logger LOGGER = Logger.getLogger(MediaCatalogController.class.getName());
    private final Catalog catalog;
    private final Random random = new Random();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");

    public MediaCatalogController() {
        catalog = new Catalog();
    }

    public void addMedia(MediaProduct mediaProduct) {
        catalog.addMediaProduct(mediaProduct);
    }

    public void deleteMedia(int id) {
        catalog.removeMediaProduct(id);
    }



    public void displayAll() {
        catalog.displayAll();
    }

    public void displayShuffle() {
        List<MediaProduct> shuffledMedia = new ArrayList<>(catalog.getMediaProducts());
        Collections.shuffle(shuffledMedia, random);
        shuffledMedia.forEach(System.out::println);
    }


    // In here, should I create a parameter as createPlaylistFromFile?
//    public void createPlaylistFromFile(String files/playlists.json) {
//        try (Stream<String> stream = Files.lines(Paths.get(files/playlists.json))) {
//            StringBuilder data = new StringBuilder();
//            stream.forEach(data::append);
//            JSONObject jsonObject = new JSONObject(data.toString());
//            JSONArray songs = jsonObject.getJSONArray("songs");
//            List<Song> songList = new ArrayList<>();
//            for (int i = 0; i < songs.length(); i++) {
//                JSONObject songObj = songs.getJSONObject(i);
//                Song song = new Song(
//                        songObj.getInt("id"),
//                        songObj.getString("name"),
//                        songObj.getString("imgdescription"),
//                        songObj.getBoolean("isFavorited"),
//                        dateFormatter.parse(songObj.getString("addedDate")),
//                        songObj.getString("artistName"),
//                        Preference.valueOf(songObj.getString("preference").toUpperCase())
//                );
//                songList.add(song);
//            }
//            Playlist playlist = new Playlist(
//                    jsonObject.getInt("id"),
//                    jsonObject.getString("name"),
//                    jsonObject.getString("description"),
//                    jsonObject.getBoolean("isFavorited"),
//                    new Date(), // Assuming date needs to be current date or parse it if included
//                    songList
//            );
//            catalog.addMediaProduct(playlist);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Error reading playlist from file", e);
//        }
//    }
}
