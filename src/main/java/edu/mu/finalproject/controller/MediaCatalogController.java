package edu.mu.finalproject.controller;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaFileReader;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MediaCatalogController {
    private static CatalogSingleton catalogSingleton;
    private static ArrayList<MediaProduct> catalogSingleton_Collection;
    private final Random random = new Random();

    public MediaCatalogController() {
        catalogSingleton = CatalogSingleton.getInstance();
        catalogSingleton_Collection = catalogSingleton.getMediaProductCollection();
    }

    public static Boolean addMedia(MediaProduct mediaProduct) {
    	if (mediaProduct == null || catalogSingleton_Collection == null) {
    		return false;
    	}
    	catalogSingleton_Collection.add(mediaProduct);
    	return true;
    }

    public static Boolean deleteMedia(int id) {
    	if (id < 0 || catalogSingleton_Collection == null) {
    		return false;
    	}
    	catalogSingleton_Collection.remove(id);
    	return true;
    }

    public static Boolean displayAll() {
    	if (catalogSingleton_Collection == null) {
    		return false;
    	}
        for (MediaProduct mediaProduct : catalogSingleton_Collection) {
        	System.out.println(mediaProduct);
        }
        return true;
    }

    public static Boolean displayShuffle() {
        if (catalogSingleton_Collection == null) {
            return false;
        }
        Collections.shuffle(catalogSingleton_Collection);
        for (MediaProduct mediaProduct : catalogSingleton_Collection) {
            System.out.println(mediaProduct);
        }
        return true;
    }

    public Boolean loadMediaFromFiles(List<? extends MediaProduct> products) {
    	if (products == null) {
    		System.err.println("Argument(s) passed to loadMediaFromFiles() are null");
    	}

    	
        for (MediaProduct product : products) {
        	addMedia(product);
        }
        return true;
    }

 
}