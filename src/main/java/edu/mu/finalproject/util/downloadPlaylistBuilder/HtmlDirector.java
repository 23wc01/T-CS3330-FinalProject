package edu.mu.finalproject.util.downloadPlaylistBuilder;

import java.util.ArrayList;

import edu.mu.finalproject.model.Account;

public class HtmlDirector {
	ArrayList<Boolean> constructionSuccess;

	public HtmlDirector() {
		this.constructionSuccess = new ArrayList<Boolean>();
	}
	
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
	
	private Boolean checkNullParams(Object model) {
		if (model == null) {
			System.err.println("Parameter(s) passed into HtmlDirector() is null");
			constructionSuccess.add(false);
			return false;	
		}
		return true;
	}
	
}
