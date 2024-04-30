package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Song;

public class SongHtmlBuilder extends HtmlBuilder {
	private Song song;

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
	public Boolean setModel(Object model) {
		if (model == null) {
			System.err.println("Parameter passed into setModel() is null");
			return false;
		}
		if (!(model instanceof Song)) {
			System.err.println("Parameter passed into setModel() is not of Song class");
			return false;
		}
		this.song = (Song) model;
		return true;
	}

	@Override
	public Boolean getDynamicFields() {
		if (song == null) {
			System.err.println("Song can't be " + song);
			return false;
		}
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
