package edu.mu.finalproject.model;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.mu.finalproject.controller.SearchController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CatalogSingleton {
    private static CatalogSingleton instance;
    private final List<MediaProduct> mediaProducts;

    
    private static final String SONGS_JSON_PATH = "files/songs.json";
    private static final String PLAYLISTS_JSON_PATH = "files/playlists.json";
    private static final String ARTISTS_JSON_PATH = "files/artists.json";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    public CatalogSingleton() {
        mediaProducts = new ArrayList<>();
        loadSongsFromJson();
        loadPlaylistsFromJson();
    }

    private Boolean loadSongsFromJson() {
        try {
        	// Reading in Songs from songs.json
        	Song song;
            String content = new String(Files.readAllBytes(Paths.get(SONGS_JSON_PATH)));
            JSONArray songs = new JSONArray(content);
            for (int i = 0; i < songs.length(); i++) {
                JSONObject songObj = songs.getJSONObject(i);
                // Assuming Song constructor takes these parameters
                song = new Song(songObj.getInt("id"), 
                		songObj.getString("name"),
                		songObj.getString("imgDescription"), 
                		songObj.getBoolean("isFavorited"),
                		(Date) dateFormat.parse(songObj.getString("addedDate")), 
                		songObj.getString("artistName"), Preference.toPreference(songObj.getString("preference")));
                mediaProducts.add(song);
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean loadPlaylistsFromJson() {
        Playlist playlist;
        Song song;
    	// Reading in Playlist
        try {
	        String content = new String(Files.readAllBytes(Paths.get(PLAYLISTS_JSON_PATH)));
	        JSONArray playlists = new JSONArray(content);
	        JSONObject playlistObj;
	        JSONArray names;
	        for (int i = 0; i < playlists.length(); i++) {
	            playlistObj = playlists.getJSONObject(i);
	            playlist = new Playlist(playlistObj.getInt("id"), 
	            		playlistObj.getString("name"), 
	            		playlistObj.getString("imgDescription"), 
	            		new Date(),
	            		playlistObj.getBoolean("isFavorited"), 
	            		new ArrayList<Song>());
	            names = playlistObj.getJSONArray("songNames");
	    
	            for (int j = 0; j < names.length(); ++j) {
	        		song = SearchController.searchSongName(names.getString(j), mediaProducts);
	        		if (song != null) {
	        			playlist.getSongs().add(song);
	        		}
	        	}
	            mediaProducts.add(playlist);
	        }
	        return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static synchronized CatalogSingleton getInstance() {
        if (instance == null) {
            instance = new CatalogSingleton();
        }
        return instance;
    }
    public static ArrayList<MediaProduct> getCatalogArrayList() {
        if (instance == null) {
            instance = new CatalogSingleton();
        }
        return (ArrayList<MediaProduct>)instance.getMediaProducts();
    }
    public Song searchMedia(String query) {
    return SearchController.searchSongName(query, mediaProducts);
    }

    public void addMediaProduct(MediaProduct mediaProduct) {
        mediaProducts.add(mediaProduct);
    }

    public void removeMediaProduct(int id) {
        mediaProducts.removeIf(m -> m.getId() == id);
    }

    public List<MediaProduct> getMediaProducts() {
        return new ArrayList<>(mediaProducts);
    }

    public void displayAll() {
        mediaProducts.forEach(System.out::println);
    }
}