package edu.mu.finalproject.model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.mu.finalproject.controller.SearchController;

public class MediaFileReader {
    private static final String SONGS_JSON_PATH = "files/songs.json";
    private static final String PLAYLISTS_JSON_PATH = "files/playlists.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    public static List<Song> readSongs() {
        List<Song> songs = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(SONGS_JSON_PATH)));
            JSONArray songsArray = new JSONArray(content);
            for (int i = 0; i < songsArray.length(); i++) {
                JSONObject songObj = songsArray.getJSONObject(i);
                Song song = new Song(
                        songObj.getInt("id"),
                        songObj.getString("name"),
                        songObj.getString("imgDescription"),
                        songObj.getBoolean("isFavorited"),
                        DATE_FORMAT.parse(songObj.getString("addedDate")),
                        songObj.getString("artistName"),
                        Preference.valueOf(songObj.getString("preference"))
                );
                songs.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static List<Playlist> readPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(PLAYLISTS_JSON_PATH)));
            JSONArray playlistsArray = new JSONArray(content);
            for (int i = 0; i < playlistsArray.length(); i++) {
                JSONObject playlistObj = playlistsArray.getJSONObject(i);
                JSONArray songNamesArray = playlistObj.getJSONArray("songNames");
                ArrayList<Song> songs = new ArrayList<Song>();
                for (int j = 0; j < songNamesArray.length(); j++) {
                    Song song = SearchController.searchSongName(songNamesArray.getString(j), CatalogSingleton.getInstance().getMediaProductCollection());
                    if (song != null) {
                        songs.add(song);
                    }
                }
                Playlist playlist = new Playlist(
                        playlistObj.getInt("id"),
                        playlistObj.getString("name"),
                        playlistObj.getString("imgDescription"),
                        new Date(),
                        playlistObj.getBoolean("isFavorited"),
                        (ArrayList)songs
                );
                playlists.add(playlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }
}
