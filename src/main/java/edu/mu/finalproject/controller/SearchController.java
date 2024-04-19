package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.view.SearchView;
import edu.mu.finalproject.util.SearchComparator;

public class SearchController {
	/**
	 * Prompts user for query, sorts, then displays all MediaObjects in @param catalog 
	 * @param catalog
	 */
	public void search(ArrayList<MediaObject> catalog) {
		SearchView searchView = new SearchView();
		String queryString = searchView.getSearchQuery();
		searchSort(queryString, catalog, searchView);
	}
	/**
	 * searches through all MediaObjects in @param catalog for MediaObjects with names similar to user's queryString 
	 * Passes sorted @param catalog to SearchView for display.
	 * @param queryString
	 * @param catalog
	 * @param searchView
	 */
	public void searchSort(String queryString, ArrayList<MediaObject> catalog, SearchView searchView) {
		SearchComparator songSearch = new SearchComparator(queryString);
		Collections.sort(catalog, songSearch);
		searchView.DisplaySearchResultsView(queryString, catalog);
		return;
	}
	
}
