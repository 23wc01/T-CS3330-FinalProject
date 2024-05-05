package edu.mu.finalproject.util.downloadPlaylistBuilder;

import java.util.ArrayList;

public class HtmlDirector {
	ArrayList<Boolean> constructionSuccess;

	public HtmlDirector() {
		setConstructionSuccess(new ArrayList<Boolean>());
	}
	
	/**
	 * Given Playlist model, build the html string for displaying Playlist's info on html file
	 * @param model
	 * @return htmlStr for Playlist
	 */
	public String constructPlaylistHtml(Object model) {
		
		if (!checkNullParams(model)) {
			return null;
		}

		PlaylistHtmlBuilder playlistHtml = new PlaylistHtmlBuilder(model);
		if (constructComponent(playlistHtml)) { // If false found in construction process
			System.out.println("Failed to construct Playlist component of html at " + constructionSuccess.contains(false));
			return null;
		}
		
		return playlistHtml.getHtmlStr();
	}
	
	/**
	 * Given Song model, build the html string for displaying Song's info on html file
	 * @param model
	 * @return htmlStr for Song
	 */
	public String constructSongHtml(Object model) {
		if (!checkNullParams(model)) {
			return null;
		}
		SongHtmlBuilder songHtml = new SongHtmlBuilder(model);
		if (constructComponent(songHtml)) { // If false found in construction process
			System.out.println("Failed to construct Song component of html");
			return null;
		}
		
		return songHtml.getHtmlStr();
	}
	
	/**
	 * Given Account model, build the html string for displaying Account's info on html file
	 * @param model
	 * @return htmlStr for Account
	 */
	public String constructAccountHtml(Object model) {
		if (!checkNullParams(model)) {
			return null;
		}
		AccountHtmlBuilder accountHtml = new AccountHtmlBuilder(model);
		if (constructComponent(accountHtml)) { // If false found in construction process
			System.out.println("Failed to construct Account component of html");
			return null;
		}
		
		return accountHtml.getHtmlStr();
	}
	
// Helper methods
	/**
	 * Execute same construction steps in order for any concrete @param htmlBuilder
	 * @param htmlBuilder
	 * @return true if at any step in construction process a method returned false.
	 */
	private Boolean constructComponent(HtmlBuilder htmlBuilder) {
		constructionSuccess.add(htmlBuilder.readTemplate());
		constructionSuccess.add(htmlBuilder.getDynamicFields());
		constructionSuccess.add(htmlBuilder.mapToHtml());
		constructionSuccess.add(htmlBuilder.createHtmlStr());
		return constructionSuccess.contains(false);
	}
	
	/**
	 * Verify model isn't null
	 * @param model
	 * @return false if model == null, else true
	 */
	private Boolean checkNullParams(Object model) {
		if (model == null) {
			System.err.println("Parameter(s) passed into HtmlDirector() is null");
			return false;	
		}
		return true;
	}
// Getters & Setters
	public ArrayList<Boolean> getConstructionSuccess() {
		return constructionSuccess;
	}
	public void setConstructionSuccess(ArrayList<Boolean> constructionSuccess) {
		this.constructionSuccess = constructionSuccess;
	}
}
