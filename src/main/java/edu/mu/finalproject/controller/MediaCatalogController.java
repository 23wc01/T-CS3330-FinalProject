package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaFileReader;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

import java.util.List;
import java.util.Random;

public class MediaCatalogController {
    private final CatalogSingleton catalogSingleton;
    private final Random random = new Random();

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

    public void displayShuffle() {
        catalogSingleton.displayShuffle();
    }

    public void loadMediaFromFiles() {
        List<Song> songs = MediaFileReader.readSongs();
        List<Playlist> playlists = MediaFileReader.readPlaylists();
        songs.forEach(catalogSingleton::addMediaProduct);
        playlists.forEach(catalogSingleton::addMediaProduct);
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