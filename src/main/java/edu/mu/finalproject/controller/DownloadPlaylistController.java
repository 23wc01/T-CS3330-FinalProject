// Source(s): https://stackoverflow.com/questions/5826384/java-iteration-through-a-hashmap-which-is-more-efficient
// https://www.educative.io/answers/what-is-the-stringutilsreplace-method-in-java
// https://www.w3schools.com/howto/howto_css_social_media_buttons.asp
package edu.mu.finalproject.controller;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.DownloadPlaylistView;


public class DownloadPlaylistController {
	private final static String htmlPlaylistTemplateFilePath = "files/playlistHtmlTemplate.txt";
	private final static String htmlSongsTemplateFilePath = "files/playlistSongsHtmlTemplate.txt";
	private final static String htmlAccountTemplateFilePath = "files/playlistAccountHtmlTemplate.txt";
	private final static String htmlOutputFolder = "outputs/";
	private String htmlOutputFilepath;
	private final static String[] playlistHtmlComponents = 
		{"@playlistName",
		"@playlistDescription",
		"@playlistSongsCount",
		"@playlistAddedDate"
		};
	private final static String[] songHtmlComponents = 
		{"@songName",
		"@songDescription",
		"@songAddedDate"
		};
	private final static String[] accountHtmlComponent = 
		{"@accountUsername"
		};
	private static String playlistHtmlTemplate;
	private static String songsHtmlTemplate;
	private static String accountHtmlTemplate;
	
	/**
	 * Reads from /files folder html template specified in @param filepath
	 * @param filepath
	 * @return
	 */
	private static String readHtmlTemplate(String filepath) {
		String html = "";
		Scanner fileInput = null;
		try {
			File htmlTemplate = new File(filepath);
			fileInput = new Scanner(htmlTemplate);
			while(fileInput.hasNextLine()) {
				html += fileInput.nextLine() + "\n";
			}
			fileInput.close();
			return html;
		}
		catch (IOException error) {
			error.printStackTrace();
			return null;
		}
	}
	
	/** 
	 * Constructor fetches 3 parts of html template & stores them in String vars
	 */
	public DownloadPlaylistController() {
		playlistHtmlTemplate = readHtmlTemplate(htmlPlaylistTemplateFilePath);
		songsHtmlTemplate = readHtmlTemplate(htmlSongsTemplateFilePath);
		accountHtmlTemplate = readHtmlTemplate(htmlAccountTemplateFilePath);
	}
	
	/**
	 * Controlling method. Gets & Writes the "dynamic values" in Playlist, Playlist's songs, and Account into html file
	 * @param playlist
	 * @param account
	 * @return
	 */
	public Boolean downloadRecommendedPlaylist(/*Account user*/) {	
		/*if ( || account == null) {
			System.out.println("To download playlist, you must login to an account");
			return false;
		}
		*/
		/*Playlist playlist = user.getUserPreference();*/
		Map<String, String> playlistDynamicValues = getPlaylistDynamicValues(playlist);
		String playlistHtml = createHtml(playlistDynamicValues, playlistHtmlTemplate);
		
		String songsHtml = "";
		for (Song song : playlist.getSongs()) {
			Map<String, String> songsDynamicValues = getSongDynamicValues(song);
			songsHtml += createHtml(songsDynamicValues, songsHtmlTemplate);
		}
		//Map<String, String> accountDynamicValues = GetAccountDynamicValues(account);
		//String accountHtml = CreateHtml(accountDynamicValues, accountHtmlTemplate);
		String html = playlistHtml + songsHtml + accountHtmlTemplate; // Change to accountHtml when Account implementation is done
		Boolean writeIsSuccess = writeHtmlToFile(playlist.getName(), html);
		DownloadPlaylistView view = new DownloadPlaylistView();
		return view.displayDownloadMessage(htmlOutputFilepath, writeIsSuccess);
	}
	
	/**
	 * Given list static HtmlComponents & list of dynamic values that'll replace the HtmlComponents, create a HashMap to map them together
	 * @param keys
	 * @param values
	 * @return static HtmlComponents mapped to their respective class's dynamic fields
	 */
	private Map<String, String> mapKeysToValues(String[] keys, String[] values) {
		Map<String, String> dynamicHtmlValues = new HashMap<String, String>();
		int index = 0;
		for(String key : keys) {
			dynamicHtmlValues.put(keys[index], values[index]);
			++index;
		}
		return dynamicHtmlValues;	
	}
	
	/**
	 * Gets desired playlist's info, stores info in list to be mapped against their static counterparts 
	 * @param playlist
	 * @return list of @param playlist's fields
	 */
	private Map<String, String> getPlaylistDynamicValues(Playlist playlist) {
		String playlistName = "";
		String playlistDescription = "";
		String playlistSongsCount = "0";
		String playlistAddedDate = "";
		
		if (playlist.getName() != null) {
			playlistName = playlist.getName();
		}
		if (playlist.getDescription() != null) {
			playlistDescription = playlist.getDescription();
		}
		if (playlist.getSongs().size() != 0) {
			playlistSongsCount = Integer.toString(playlist.getSongs().size());
		}
		if (playlist.getAddedDate() != null) {
			playlistAddedDate = playlist.getAddedDate().toString();
		}
		String[] playlistInfo = {playlistName, playlistDescription, playlistSongsCount, playlistAddedDate};
		return mapKeysToValues(playlistHtmlComponents, playlistInfo);
	}
	/**
	 * Gets desired song's info, stores info in list to be mapped against their static counterparts 
	 * @param song
	 * @return list of @param song's fields
	 */
	private Map<String, String> getSongDynamicValues(Song song) {
		String songName = "";
		String songDescription = "music";
		String songAddedDate = "";
		if (song.getName() != null) {
			songName = song.getName();
		}
		if (song.getDescription() != null) {
			songDescription = song.getDescription();
		}
		
		if (song.getAddedDate() != null) {
			songAddedDate = song.getAddedDate().toString();
		}

		String[] songInfo = {songName, songDescription, songAddedDate};
		return mapKeysToValues(songHtmlComponents, songInfo);
	}
	
	/**
	 * Gets account's info, stores account in list to be mapped against their static counterparts 
	 * @param song
	 * @return list of @param user's fields
	 */
	private Map<String, String> getAccountDynamicValues(/*, Account user*/) {
		String username = "";
		/*
		if (playlist.getArtistID() != null) {
			username = user.getUsername();
		}
		*/
		String[] accountInfo = {username};
		return mapKeysToValues(accountHtmlComponent, accountInfo); 
	}
	
	/**
	 * Finds & Replace htmlComponents with their dynamic values
	 * @param dynamicHtmlValues
	 * @param html
	 * @return html populated with dynamic fields
	 */
	private String createHtml(Map<String, String> dynamicHtmlValues, String html) {
		for (Map.Entry<String, String> htmlComponent : dynamicHtmlValues.entrySet()) {
			html = StringUtils.replace(html, htmlComponent.getKey(), htmlComponent.getValue(), 3);
		}
		return html;
	}
	
	/**
	 * Write to @param filename.html the string html
	 * @param filename
	 * @param html
	 * @return Boolean indicating success/failure to write html file
	 */
	private Boolean writeHtmlToFile(String filename, String html) {
		htmlOutputFilepath = htmlOutputFolder + filename + ".html";
		htmlOutputFilepath = htmlOutputFilepath.replaceAll(" ", "_");
		try {
			FileWriter writer = new FileWriter(htmlOutputFilepath);
			writer.write(html);
			writer.close();
			return true;
	    }
		catch (IOException e) {
			// Uncomment if debugging needed
			// e.printStackTrace();
			return false;
		}
	}
}
