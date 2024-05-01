package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.Catalog;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.model.Preference;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Logger;
import java.util.logging.Level;

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

    public MediaProduct createMedia(String type, Map<String, String> attributes) {
        try {
            int id = Integer.parseInt(attributes.get("id"));
            String name = attributes.get("name");
            String description = attributes.get("description");
            Date addedDate = dateFormatter.parse(attributes.get("addedDate"));
            Boolean isFavorited = Boolean.parseBoolean(attributes.get("isFavorited"));
            String artistName = attributes.get("artistName");
            Preference preference = Preference.valueOf(attributes.get("preference").toUpperCase());
            return new Song(id, name, description, isFavorited, addedDate, artistName, preference);
        } catch (NumberFormatException | ParseException e) {
            LOGGER.log(Level.SEVERE, "Error creating media based on type and attributes", e);
            return null;
        }
    }


    public void displayAll() {
        catalog.displayAll();
    }

    public void displayShuffle() {
        List<MediaProduct> shuffledMedia = new ArrayList<>(catalog.getMediaProducts());
        Collections.shuffle(shuffledMedia, random);
        shuffledMedia.forEach(System.out::println);
    }
}
