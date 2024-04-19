package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.view.SearchView;
import edu.mu.finalproject.util.SearchComparator;

public class SearchController {
	public void search(ArrayList<MediaObject> catalog) {
		SearchView searchView = new SearchView();
		String queryString = searchView.getSearchQuery();
		searchSort(queryString, catalog, searchView);
	}
	public void searchSort(String queryString, ArrayList<MediaObject> catalog, SearchView searchView) {
		SearchComparator songSearch = new SearchComparator(queryString);
		Collections.sort(catalog, songSearch);
		searchView.DisplaySearchResultsView(queryString, catalog);
		return;
	}
	
}
