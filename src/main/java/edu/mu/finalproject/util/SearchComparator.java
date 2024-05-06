/*Sources: 
	https://www.baeldung.com/java-comparator-comparable
	https://itsallbinary.com/similar-strings-or-same-sounding-strings-algorithms-comparison-apache-implementation-in-java/#fuzzy
*/
package edu.mu.finalproject.util;

import java.util.Comparator;
import edu.mu.finalproject.model.MediaProduct;

import java.util.Locale;
import org.apache.commons.text.similarity.*;


	public class SearchComparator implements Comparator<MediaProduct> {
	private final String queryString; 
	
	/**
	 * Constructor will take queryName that can't be changed after SearchComparator created
	 * @param queryName
	 */
	public SearchComparator(String queryName) {
		this.queryString = queryName;
	}
	
	/**
	 * This compares the FuzzyScore (case insensitive & 1 point added for each char matched) between 2 MediaObject's name and queryString. 
	 * @param obj1
	 * @param obj2
	 * @return fuzzyScoresComparison (1 = obj2's name more similar to queryString than obj1's name). -1 if error occurs
	 */
	@Override
	public int compare(MediaProduct obj1, MediaProduct obj2) {
		try { // Ensure no null values passed into FuzzyScore()
			int similarity1 = new FuzzyScore(Locale.getDefault()).fuzzyScore(obj1.getName(), queryString);
			int similarity2 = new FuzzyScore(Locale.getDefault()).fuzzyScore(obj2.getName(), queryString);
			return Integer.compare(similarity2, similarity1);
		}
		catch (Exception e) {
			return -1;
		}
	}

}
