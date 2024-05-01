package edu.mu.finalproject.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<MediaProduct> mediaProducts;

    public Catalog() {
        mediaProducts = new ArrayList<>();
    }

    public void addMediaProduct(MediaProduct mediaProduct) {
        mediaProducts.add(mediaProduct);
    }

    public boolean removeMediaProduct(int id) {
        return mediaProducts.removeIf(m -> m.getId() == id);
    }

    public List<MediaProduct> getMediaProducts() {
        return new ArrayList<>(mediaProducts); // Return a copy to ensure encapsulation
    }

    public void displayAll() {
        mediaProducts.forEach(System.out::println);
    }
}
