package edu.mu.finalproject.model;

import java.util.ArrayList;

public class CatalogSingleton {
    private static CatalogSingleton instance;
    public static ArrayList <MediaProduct> mediaProductCollection = new ArrayList<MediaProduct>(); 

    public static CatalogSingleton getInstance() {
        if (instance == null) {
            instance = new CatalogSingleton();
        }
        return instance;
    }

    private CatalogSingleton() {
    }
    public ArrayList<MediaProduct> getMediaProductCollection() {
		return mediaProductCollection;
	}
    public static boolean setMediaProductCollection(ArrayList<MediaProduct> mediaProductCollection) {
		if (mediaProductCollection == null) {
			return false;
		}
		CatalogSingleton.mediaProductCollection = mediaProductCollection;
		return true;
    }
}