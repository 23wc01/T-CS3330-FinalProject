package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.FindObjectView;


/**
 * 
 */
public class FindObjectController {
	
	public MediaProduct searchMediaCatalog(ArrayList <? extends MediaProduct> catalog, String userQuery){ 
		
		if(userQuery == null) {
			System.out.println("Failed to retrieve information.");
			return null;
		}
		
		for(MediaProduct traverseObject : catalog) { 
			if (traverseObject.getName().equalsIgnoreCase(userQuery)) {
				return traverseObject;
			}
		}
			return null;
	}

}