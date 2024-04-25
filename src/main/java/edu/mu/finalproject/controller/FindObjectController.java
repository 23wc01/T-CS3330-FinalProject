package edu.mu.finalproject.controller;

import java.util.ArrayList;

import edu.mu.finalproject.model.MediaObject;
import edu.mu.finalproject.view.FindObjectView;


/**
 * 
 */
public class FindObjectController {
	
	public static MediaObject searchMediaCatalog(ArrayList <? extends MediaObject> catalog){ //controller
		String userQuery = FindObjectView.getInformationFromUser();
		
		if(userQuery == null) {
			System.out.println("Failed to retieve information.");
		}
		
		for(MediaObject traverseObject : catalog) { 
			if (traverseObject.getName().equalsIgnoreCase(userQuery)) {
				return traverseObject;
			}
		}
			return null;
	}

}
