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
		System.out.println("Search for any media product based on their name...");
		System.out.print("Enter search (max char = " + MAX_SEARCH_CHARS + "): ");
		Scanner searchScanner = new Scanner(System.in);
		return scanQuery(searchScanner);
	}
	
	/**
	 * Scan in user's input query
	 * @param searchScanner
	 * @return
	 */
	public String scanQuery(Scanner searchScanner) {
		try {
			String searchQuery = searchScanner.nextLine();
			searchQuery = searchQuery.substring(0, Math.min(searchQuery.length(), MAX_SEARCH_CHARS));
			return searchQuery;
		}
		catch (Exception e){
			System.out.println("Error with passed in scanner");
			return null;
		}
	}
	
	/**
	 * Displays @param topNResults of MediaObjects in @param catalog sorted in descending order of fuzzyscore similarity to @param queryString
	 * @param queryString
	 * @param topNResults
	 * @param sortedCatalog
	 * @return false if parameters are null, else true
	 */
	public Boolean displaySearchResults(String queryString, int topNResults, ArrayList<MediaProduct> sortedCatalog) {
		if (sortedCatalog == null || queryString == null || topNResults < 1) {
			return false;
		}
		System.out.println("\nTop " + topNResults + " search results for '"+ queryString + "'");
		System.out.println("-------------------------------");
		int resultCount = 0;
		for (MediaProduct mediaObj : sortedCatalog) {
			if(resultCount == topNResults) {
				break;
			}
			System.out.println(mediaObj.toString());
			++resultCount;
		}
		System.out.println("-------------------------------\n");
		return true;
	}
}
