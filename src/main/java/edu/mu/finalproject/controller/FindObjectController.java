package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaProduct;
import edu.mu.finalproject.view.FindObjectView;


/**
 * 
 */
public class FindObjectController {
	
	public static MediaProduct searchMediaCatalog(ArrayList <? extends MediaProduct> catalog){ //controller
		String userQuery = FindObjectView.getInformationFromUser();
		
		if(userQuery == null) {
			System.out.println("Failed to retieve information.");
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
