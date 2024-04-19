package edu.mu.finalproject.view;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaObject;

public class SearchView {
	public void DisplaySearchResultsView(String queryString, ArrayList<MediaObject> sortedCatalog) {
		System.out.println("\nSearch results for '"+ queryString + "'");
		System.out.println("-------------------------------");
		for (MediaObject mediaObj : sortedCatalog) {
			System.out.println(mediaObj.toString());
		}
		System.out.println("-------------------------------\n");
	}
}
