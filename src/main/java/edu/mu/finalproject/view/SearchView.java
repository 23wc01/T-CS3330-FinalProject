// Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.mu.finalproject.model.MediaProduct;

public class SearchView {
	private final static int  MAX_SEARCH_CHARS = 50;
	/**
	 * Prompts user to enter a name (max 50 chars) to search by. Returns user inputted search as @queryString
	 * @return queryString, will be null if input mismatch detected
	 */
	public String getSearchQuery() {
		System.out.println("Search anything/anyone based on their name...");
		System.out.print("Enter search (max char = " + MAX_SEARCH_CHARS + "): ");
		Scanner searchScanner = new Scanner(System.in);
		try {
			String searchQuery = searchScanner.nextLine();
			searchQuery = searchQuery.substring(0, Math.min(searchQuery.length(), MAX_SEARCH_CHARS));
			return searchQuery;
		}
		catch (InputMismatchException e){
			return null;
		}
	}
	
	/**
	 * Displays @param catalog with all MediaObjects inside sorted in descending order of fuzzyscore similarity to @param queryString
	 * @param queryString
	 * @param sortedCatalog
	 * @return false if parameters are null, else true
	 */
	public Boolean DisplaySearchResultsView(String queryString, int topNSearch, ArrayList<MediaProduct> sortedCatalog) {
		if (queryString == null || sortedCatalog == null) {
			return false;
		}
		System.out.println("\nTop " + topNSearch + " search results for '"+ queryString + "'");
		System.out.println("-------------------------------");
		int resultCount = 0;
		for (MediaProduct mediaObj : sortedCatalog) {
			if(resultCount == topNSearch) {
				break;
			}
			System.out.println(mediaObj.toString());
			++resultCount;
		}
		System.out.println("-------------------------------\n");
		return true;
	}
}
