package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Playlist;

public class PlaylistHtmlBuilder extends HtmlBuilder {
	private Playlist playlist;
	
	/**
	 * Initializes html template file path. Will search through html template for what's added to replaceHtml
	 * Sets the default value that'll replaceHtml if anything goes wrong with fetching fields from model 
	 * @param model
	 */
	public PlaylistHtmlBuilder(Object model) {
		super();
		Boolean success = setModel(model);
		if (!success) {
			return;
		}
		getReplaceHtml().add("@playlistName");
		getReplaceHtml().add("@playlistDescription");
		getReplaceHtml().add("@playlistSongsCount");
		getReplaceHtml().add("@playlistAddedDate");
		
		getModelFields().add("Playlist");
		getModelFields().add("playlist");
		getModelFields().add("0");
		getModelFields().add("");
		setTemplateFileLocation("files/playlistHtmlTemplate.txt");
	}

	@Override
	/**
	 * Sets playlist using @param model
	 * @return true
	 */
	protected Boolean setModel(Object model) {
		this.playlist = (Playlist) model;
		return true;
	}

	@Override
	/**
	 * Gets the dynamic field that'll replace replaceHtml. If field = null will do nothing (thus, falling back on default values set in constructor)
	 * @return true
	 */
	protected Boolean getDynamicFields() {
		if (playlist.getName() != null && playlist.getName() != "") {
			this.getModelFields().set(0, playlist.getName());
		}
		if (playlist.getImgDescription() != null && playlist.getImgDescription() != "") {
			this.getModelFields().set(1, playlist.getImgDescription());
		}
		if (playlist.getSongs().size() != 0) {
			this.getModelFields().set(2, Integer.toString(playlist.getSongs().size()));
		}
		if (playlist.getAddedDate() != null) {
			this.getModelFields().set(3, playlist.getAddedDate().toString());
		}
		
		return true;
	}
}
