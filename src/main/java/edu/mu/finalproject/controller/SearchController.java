
package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.SearchView;
import edu.mu.finalproject.util.SearchComparator;

public class SearchController {
	SearchView view;
	public SearchController() {
		view = new SearchView();
	}
	
	/**
	 * Prompts user for query, sorts, then displays all MediaObjects in @param catalog 
	 * @param catalog
	 * @return false if search
	 */
	public Boolean search(ArrayList<MediaProduct> catalog) {
		String searchQuery = view.getSearchQuery();
		return searchSort(searchQuery, catalog);
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
	private Boolean searchSort(String searchQuery, ArrayList<MediaProduct> catalog) {
		if (searchQuery == null) {
			System.out.println("Did not enter a query.");
			return false;
		}
		SearchComparator songSearch = new SearchComparator(searchQuery);
		Collections.sort(catalog, songSearch);
		view.DisplaySearchResultsView(searchQuery, catalog);
		return true;
	}
	
}