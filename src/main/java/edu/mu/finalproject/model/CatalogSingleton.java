package edu.mu.finalproject.model;

import java.util.List;

public class CatalogSingleton extends MediaCatalog {
    private static CatalogSingleton instance;

    public static synchronized CatalogSingleton getInstance() {
        if (instance == null) {
            instance = new CatalogSingleton();
        }
        return instance;
    }

    private CatalogSingleton() {
        super();
    }
}