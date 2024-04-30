package edu.mu.finalproject.util.downloadPlaylistBuilder;

public enum ModelHtml {
	PLAYLIST_HTML("files/playlistHtmlTemplate.txt"),
	SONG_HTML("files/playlistSongsHtmlTemplate.txt"),
	ACCOUNT_HTML("files/playlistAccountHtmlTemplate.txt");
	
	private final String templateFilepath;
	/**
	 * Constructed ModelHtml enum always stored with its template html's filepath
	 * @param templateFilepath
	 */
	private ModelHtml(String templateFilepath) {
		this.templateFilepath = templateFilepath;
	}
	
	/**
	 * Returns the filepath matching ModelHtml enum
	 * @return
	 */
	public String getTemplateFilepath() {
		return templateFilepath;
	}
}
