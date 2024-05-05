package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Song;

public class SongHtmlBuilder extends HtmlBuilder {
	private Song song;

	/**
	 * Initializes html template file path. Will search through html template for what's added to replaceHtml
	 * Sets the default value that'll replaceHtml if anything goes wrong with fetching fields from model 
	 * @param model
	 */
	public SongHtmlBuilder(Object model) {
		super();
		Boolean success = setModel(model);
		if (!success) {
			return;
		}
		getReplaceHtml().add("@songName");
		getReplaceHtml().add("@songDescription");
		getReplaceHtml().add("@songAddedDate");
		
		getModelFields().add("Unamed");
		getModelFields().add("music");
		getModelFields().add("");
		setTemplateFileLocation("files/playlistSongsHtmlTemplate.txt");
	}
	@Override
	/**
	 * Sets song using @param model
	 * @return true
	 */
	protected Boolean setModel(Object model) {
		this.song = (Song) model;
		return true;
	}

	@Override
	/**
	 * Gets the dynamic field that'll replace replaceHtml. If field = null will do nothing (thus, falling back on default values set in constructor)
	 * @return true
	 */
	protected Boolean getDynamicFields() {

		if (song.getName() != null) {
			getModelFields().set(0, song.getName());
		}
		if (song.getImgDescription() != null) {
			getModelFields().set(1, song.getImgDescription());
		}
		if (song.getAddedDate() != null) {
			getModelFields().set(2, song.getAddedDate().toString());
		}

		return true;
	}

}
