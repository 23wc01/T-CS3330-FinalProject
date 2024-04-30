// Source(s): https://stackoverflow.com/questions/5826384/java-iteration-through-a-hashmap-which-is-more-efficient
// https://www.educative.io/answers/what-is-the-stringutilsreplace-method-in-java
// https://www.w3schools.com/howto/howto_css_social_media_buttons.asp
package edu.mu.finalproject.controller;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.util.downloadPlaylistBuilder.HtmlDirector;
import edu.mu.finalproject.view.DownloadPlaylistView;


public class DownloadPlaylistController {
	private final static String htmlOutputFolder = "outputs/";
	private ArrayList<String> htmlStrs;
	private String htmlOutputFilepath;
	private Playlist recommendedPlaylist;
	private DownloadPlaylistView view;
	
	/** 
	 * Constructor fetches 3 parts of html template & stores them in String vars
	 */
	public DownloadPlaylistController() {
		htmlStrs = new ArrayList<String>();
		htmlOutputFilepath = htmlOutputFolder;
		view = new DownloadPlaylistView();
	}
	
	/** 
	 * Controlling method. Gets & Writes the "dynamic values" in Playlist, Playlist's songs, and Account into html file
	 * @param account
	 * @param catalog
	 * @return
	 */
	public Boolean downloadRecommendedPlaylist(Account account, ArrayList<MediaProduct> catalog) {	
		if (!createPlaylist(account, catalog)) {
			System.err.println("Couldn't create playlist for downloadRecommendedPlaylist()");
			return false;
		}
		
		HtmlDirector director = new HtmlDirector();
		
		htmlStrs.add(director.constructPlaylistHtml(getRecommendedPlaylist()));
		for (Song song : getRecommendedPlaylist().getSongs()) {
			htmlStrs.add(director.constructSongHtml(song));
		}
		htmlStrs.add(director.constructAccountHtml(account));		
		String combinedStr = combineHtmlStrs();
		if (combinedStr == null) {
			return false;
		}
		Boolean writeIsSuccess = writeHtmlToFile(getRecommendedPlaylist().getName(), combinedStr);
		
		return view.displayDownloadMessage(htmlOutputFilepath, writeIsSuccess);
	}
	
// HELPER FUNCTIONS
	/**
	 * Creates controller to create playlist of recommended songs
	 * @param account
	 * @param catalog
	 * @return
	 */
	public Boolean createPlaylist(Account account, ArrayList<MediaProduct> catalog) {
		if (account == null || catalog == null) {
			System.out.println("\nParameter(s) passed into createPlaylist() are null");
			return false;
		}
		RecommendPlaylistController recommendPlaylistController = new RecommendPlaylistController();
		setRecommendedPlaylist(recommendPlaylistController.recommendPlaylist(account.getUserPreference(), catalog));
		if (getRecommendedPlaylist() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * For all htmlStrs in htmlStrs, combine them into 1 string. 
	 * @return null if any constructed htmlStr == null
	 */
	private String combineHtmlStrs() {
		String combinedStr = "";
		if(htmlStrs.contains(null)) {
			return null;
		}
		for (String htmlStr : htmlStrs) {
			combinedStr += htmlStr;
		}
		return combinedStr;
	}
	
	/**
	 * Write to @param filename.html combined htmlStrs
	 * @param filename
	 * @param html
	 * @return Boolean indicating success/failure to write html file
	 */
	private Boolean writeHtmlToFile(String filename, String html) {
		filename = filename.replaceAll(" ", "_");
		htmlOutputFilepath += filename + ".html";

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
	
// GETTERS & SETTERS
	public Playlist getRecommendedPlaylist() {
		return recommendedPlaylist;
	}
	public void setRecommendedPlaylist(Playlist recommendedPlaylist) {
		this.recommendedPlaylist = recommendedPlaylist;
	}
}
