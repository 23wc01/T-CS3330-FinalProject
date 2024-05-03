package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaFileReader;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediaCatalogController {
    private final CatalogSingleton catalogSingleton;
    private final ArrayList<MediaProduct> catalogSingleton_Collection;
    private final Random random = new Random();

    public MediaCatalogController() {
        catalogSingleton = CatalogSingleton.getInstance();
        catalogSingleton_Collection = catalogSingleton.getMediaProductCollection();
    }

    public void addMedia(MediaProduct mediaProduct) {
    	catalogSingleton_Collection.add(mediaProduct);
    }

    public void deleteMedia(int id) {
    	catalogSingleton_Collection.remove(id);
    }

    public void displayAll() {
        for (MediaProduct mediaProduct : catalogSingleton_Collection) {
        	System.out.println(mediaProduct);
        }
    }

    public void displayShuffle() {
    	catalogSingleton_Collection.displayShuffle();
    }

    public void loadMediaFromFiles() {
        List<Song> songs = MediaFileReader.readSongs();
        List<Playlist> playlists = MediaFileReader.readPlaylists();
        for (Song song : songs) {
        	addMedia(song);
        }
        for (Playlist playlist : playlists) {
        	addMedia(playlist);
        }
    }

 
}