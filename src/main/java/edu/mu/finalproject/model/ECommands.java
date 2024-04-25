package edu.mu.finalproject.model;

public enum ECommands {
	
	//Add your commands to the enum as you go along! Try to put it in alphabetical so its easier to look through, but leave invalid at the bottom
	//The type name goes in all caps, and what you want the user to put into the console goes in the parenthesis
	//The descriptions (things in parenthesis) have to match the enum name exactly. Other wise it won't work. 
	//If you want to play around with formatting input and stuff in UserInterface, you can, I just didn't think we needed it now.
	ADD_EVENT("add_event"),
	DELETE_EVENT("delete_event"),
	DISPLAY_EVENTS("display_events"),
	EXIT("exit"),
	//WAITING FOR CATALOG: FAVORITE("favorite"),
	MENU("menu"),
	//New commands here!
	SETUP_PREFERENCE("setup_preference"),
	//WAITING FOR CATALOG: SEARCH("search"),
	//WAITING FOR CATALOG: DOWNLOAD_PLAYLIST("download_playlist"),
	//WAITING FOR ACCOUNT: RECOMMEND_BY_PREFERENCE("recommend_by_preference),
	INVALID("");
	
	private String description;
	
	//Constructor
	ECommands(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	

}



