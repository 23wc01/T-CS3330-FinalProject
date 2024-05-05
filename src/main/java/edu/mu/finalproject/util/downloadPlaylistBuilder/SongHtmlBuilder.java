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
	protected Boolean setModel(Object model) {
		this.song = (Song) model;
		return true;
	}

	@Override
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
