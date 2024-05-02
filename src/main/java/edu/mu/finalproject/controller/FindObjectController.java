package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.FindObjectView;


/**
 * Accesses the catalog and searches for an object that extends MediaProduct
 * Works closely with class FindObjectView
 * 
 * Used for core feature "favorite"
 * @author etwil
 */
public class FindObjectController {
	
	/**
	 * 
	 * @param ArrayList catalog
	 * @param String userQuery
	 * @return null - if the user query is null or if object is not found
	 * @return MediaProduct object - the object who's name matches the userQuery
	 * @author etwil
	 */
	public MediaProduct searchMediaCatalog(ArrayList <? extends MediaProduct> catalog, String userQuery){ 
		
		if(userQuery == null) {
			return null;
		}
		// Iterate through catalog until the name matches the user query
		for(MediaProduct traverseObject : catalog) { 
			if (traverseObject.getName().equalsIgnoreCase(userQuery)) {
				return traverseObject;
			}
		}
			return null;
	}

}