// Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.model.MediaObject;

public class SearchView {
	private final static int  MAX_SEARCH_CHARS = 50;
	public String getSearchQuery() {
		System.out.print("Enter your search (max char = 50): ");
		Scanner scanner = new Scanner(System.in);
		String queryString = scanner.nextLine();
		queryString = queryString.substring(0, Math.min(queryString.length(), MAX_SEARCH_CHARS));
		return queryString;
	}
	public void DisplaySearchResultsView(String queryString, ArrayList<MediaObject> sortedCatalog) {
		System.out.println("\nSearch results for '"+ queryString + "'");
		System.out.println("-------------------------------");
		for (MediaObject mediaObj : sortedCatalog) {
			System.out.println(mediaObj.toString());
		}
		System.out.println("-------------------------------\n");
	}
}
