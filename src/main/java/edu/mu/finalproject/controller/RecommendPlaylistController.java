package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Date;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Playlist;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.RecommendPlaylistView;

public class RecommendPlaylistController {
	private RecommendPlaylistView recommendedView;
	private Playlist recommendedPlaylist;

	public RecommendPlaylistController() {
		this.recommendedView = new RecommendPlaylistView();
	}
	
 	/**
	 * Create a playlist storing only songs in @param catalog that match @param preference
	 * @param catalog
	 * @param preference
	 * @return
	 */
	public Playlist recommendPlaylist(Preference preference) {
		if (initializeRecommendedPlaylist(preference)) {
			for (MediaProduct obj : CatalogSingleton.getCatalogArrayList()) {
				if (obj instanceof Song) {
					Song song = (Song) obj;
					if(song.getPreference() == preference) {
						recommendedPlaylist.getSongs().add(song);
					}
				}
			}
			recommendedView.displaySongRecommendations(recommendedPlaylist.getImgDescription(), recommendedPlaylist.getSongs());

			return recommendedPlaylist;
		}
		
		else {
			System.err.println("Setup a preference first with 'setup_preference'! (Parameter(s) passed into recommendPlaylist() are null)");
			return null;
		}
	}
	
// HELPER METHOD(S)
	
	/**
	 * Initialize playlist to 
	 * @param preference
	 * @return false if preference = null, else true
	 */
	private Boolean initializeRecommendedPlaylist(Preference preference) {
		if(preference == null) {
			return false;
		}
		String preferenceStr = preference.capitalizePreference();
		this.recommendedPlaylist = new Playlist(0, preferenceStr + " songs", preferenceStr, new Date(), false, new ArrayList<Song>());
		return true;
	}
	

}
