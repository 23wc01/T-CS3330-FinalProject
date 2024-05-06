package edu.mu.finalproject.view;

import edu.mu.finalproject.controller.MediaCatalogController;
import edu.mu.finalproject.model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CatalogView {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayAllMedia() {
        System.out.println("Displaying all media:");
        if (!MediaCatalogController.displayAll()) {
            System.out.println("The catalog is currently empty.");
        }
    }

    public static void addMedia() {
        System.out.println("Enter the type of media (Song/Playlist):");
        String type = scanner.nextLine().trim();
        MediaProduct media = createMediaProductBasedOnType(type);
        if (media != null && MediaCatalogController.addMedia(media)) {
            System.out.println("Media added successfully!");
        } else {
            System.out.println("Failed to add media.");
        }
    }

    private static MediaProduct createMediaProductBasedOnType(String type) {
        if (type.equalsIgnoreCase("Song")) {
            return createSong();
        } else if (type.equalsIgnoreCase("Playlist")) {
            return createPlaylist();
        } else {
            System.out.println("Unknown media type.");
            return null;
        }
    }

    private static Song createSong() {
        try {
            System.out.println("Enter Song ID:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Song Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Description:");
            String description = scanner.nextLine();
            System.out.println("Is the song favorited? (true/false):");
            boolean isFavorited = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Enter Added Date (MM-dd-yyyy):");
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date addedDate = sdf.parse(scanner.nextLine());
            System.out.println("Enter Artist Name:");
            String artistName = scanner.nextLine();
            System.out.println("Enter Preference (RAP, POP, EDM, ROCK, SOUL, COUNTRY):");
            String preferenceStr = scanner.nextLine();
            Preference preference = Preference.toPreference(preferenceStr);

            return new Song(id, name, description, isFavorited, addedDate, artistName, preference);
        } catch (Exception e) {
            System.out.println("Error creating song: " + e.getMessage());
            return null;
        }
    }

    private static Playlist createPlaylist() {
        try {
            System.out.println("Enter Playlist ID:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Playlist Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Description:");
            String description = scanner.nextLine();
            System.out.println("Is the playlist favorited? (true/false):");
            boolean isFavorited = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Enter Added Date (MM-dd-yyyy):");
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date addedDate = sdf.parse(scanner.nextLine());
            ArrayList<Song> songs = new ArrayList<>();

            return new Playlist(id, name, description, addedDate, isFavorited, songs);
        } catch (Exception e) {
            System.out.println("Error creating playlist: " + e.getMessage());
            return null;
        }
    }

    public static void deleteMedia() {
        System.out.println("Enter the ID of the media to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        if (MediaCatalogController.deleteMedia(id)) {
            System.out.println("Media deleted successfully!");
        } else {
            System.out.println("Failed to delete media. Please check the ID.");
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:\n1. Display All Media\n2. Add Media\n3. Delete Media\n4. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayAllMedia();
                    break;
                case 2:
                    addMedia();
                    break;
                case 3:
                    deleteMedia();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
