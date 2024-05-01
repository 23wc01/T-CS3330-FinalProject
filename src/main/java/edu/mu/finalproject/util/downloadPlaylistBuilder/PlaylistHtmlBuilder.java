package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Playlist;

public class PlaylistHtmlBuilder extends HtmlBuilder {
	Playlist playlist;

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
	protected Boolean setModel(Object model) {
		if (model == null) {
			System.err.println("Parameter passed into setModel() is null");
			return false;
		}
		if (!(model instanceof Playlist)) {
			System.err.println("Parameter passed into setModel() is not of Playlist class");
			return false;
		}
		this.playlist = (Playlist) model;
		return true;
	}

	@Override
	protected Boolean getDynamicFields() {
		if (playlist == null) {
			System.err.println("Playlist can't be " + playlist);
			return false;
		}
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
