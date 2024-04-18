// Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.model.MediaObject;

public class SearchView {
	private final static int  MAX_SEARCH_CHARS = 50;
	/**
	 * Prompts user to enter a name (max 50 chars) to search by. Returns user inputted search as @queryString
	 * @return queryString
	 */
	public String getSearchQuery() {
		System.out.println("Search anything/anyone based on their name...");
		System.out.print("Enter search (max char = 50): ");
		Scanner scanner = new Scanner(System.in);
		String queryString = scanner.nextLine();
		queryString = queryString.substring(0, Math.min(queryString.length(), MAX_SEARCH_CHARS));
		return queryString;
	}
	/**
	 * Displays @param catalog with all MediaObjects inside sorted in descending order of fuzzyscore similarity to @param queryString
	 * @param queryString
	 * @param sortedCatalog
	 */
	public void DisplaySearchResultsView(String queryString, ArrayList<MediaObject> sortedCatalog) {
		System.out.println("\nSearch results for '"+ queryString + "'");
		System.out.println("-------------------------------");
		for (MediaObject mediaObj : sortedCatalog) {
			System.out.println(mediaObj.toString());
		}
		System.out.println("-------------------------------\n");
	}
}
