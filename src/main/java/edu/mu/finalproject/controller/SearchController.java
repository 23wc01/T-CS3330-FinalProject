package edu.mu.finalproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.view.SearchView;
import edu.mu.finalproject.util.SearchComparator;

public class SearchController {
	public void searchSort(String queryString, ArrayList<MediaObject> catalog) {
		SearchComparator songSearch = new SearchComparator(queryString);
		Collections.sort(catalog, songSearch);
		SearchView searchResultsView = new SearchView();
		searchResultsView.DisplaySearchResultsView(queryString, catalog);
		return;
	}
	
}
