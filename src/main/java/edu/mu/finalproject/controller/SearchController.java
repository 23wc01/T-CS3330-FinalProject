
package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.mu.finalproject.model.CatalogSingleton;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.model.Song;
import edu.mu.finalproject.view.SearchView;
import edu.mu.finalproject.util.SearchComparator;

public class SearchController {
	private SearchView view;
	static public int topNSearch;

	public SearchController() {
		view = new SearchView();
		setTopNSearch(5);
	}
	
	/**
	 * Prompts user for query, sorts, then displays all MediaObjects in @param catalog 
	 * @param catalog
	 * @return false if search
	 */
	public Boolean search() {
		String searchQuery = view.getSearchQuery();
		return searchSort(searchQuery);
	}


// HELPER FUNCTION(S)	
	
	/**
	 * Searches through all MediaObjects in @param catalog for MediaObjects with names similar to user's queryString 
	 * Passes sorted @param catalog to SearchView for display.
	 * @param queryString
	 * @param catalog
	 * @param searchView
	 * @return false if queryString is null, else true
	 */
	private Boolean searchSort(String searchQuery) {
		ArrayList<MediaProduct> catalog = CatalogSingleton.getCatalogArrayList();
		if (searchQuery == null || catalog == null) {
			System.out.println("Did not enter a query.");
			return false;
		}
		SearchComparator songSearch = new SearchComparator(searchQuery);
		Collections.sort(catalog, songSearch);
		view.DisplaySearchResultsView(searchQuery, getTopNSearch(), catalog);
		return true;
	}

// EXTRA FEATURES 

	/**
	 * This functionality/feature is used to find Song in CatalogSingleton given PRECISE song's name 
	 * @param songName
	 */
	public static Song searchSongName(String songName, List<MediaProduct> mediaProducts) {
		if(mediaProducts == null || songName == null || songName == "") {
			System.err.println("Failed to retieve song because songName = " + songName);
			return null;
		}
		for(MediaProduct traverseObject : mediaProducts) { 
			if (traverseObject.getName().equalsIgnoreCase(songName)) {
				if(traverseObject instanceof Song) {
					return (Song) traverseObject;
				}
			}
		}
		return null;
	}
// GETTER & SETTER
	public static int getTopNSearch() {
		return topNSearch;
	}

	public static Boolean setTopNSearch(int topSearch) {
		if (topSearch < 1) {
			return false;
		}
		SearchController.topNSearch = topSearch;
		return true;
	}
}