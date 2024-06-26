package edu.mu.finalproject.model;

/**
 * This enum stores all commands and corresponding descriptions
 * The descriptions are to match user input with one of the enum values
 * 
 *  Works closely with class UserInterface
 */
public enum ECommands {
	
	//Add your commands to the enum as you go along! Try to put it in alphabetical so its easier to look through, but leave invalid at the bottom
	//The type name goes in all caps, and what you want the user to put into the console goes in the parenthesis
	//The descriptions (things in parenthesis) have to match the enum name exactly. Other wise it won't work. 
	//If you want to play around with formatting input and stuff in UserInterface, you can, I just didn't think we needed it now.
	ADD_EVENT("add_event"),
	CHANGE_PASSWORD("change_password"),
	CHANGE_USERNAME("change_username"),
	DELETE_ACCOUNT("delete_account"),
	DELETE_EVENT("delete_event"),
	DISPLAY_EVENTS("display_events"),
	DISPLAY_FAVORITES("display_favorites"),
	//WAITING FOR CATALOG: DOWNLOAD_PLAYLIST("download_playlist"),
	FAVORITE("favorite"),
	FOLLOW_USER("follow_user"),
	SAVE_PLAYLIST("save_playlist"),
	SAVE_SONG("save_song"),
	SEARCH("search"),
	SETUP_PREFERENCE("setup_preference"),
	RECOMMEND_PLAYLIST("recommend_playlist"),
	DOWNLOAD_RECOMMENDED_PLAYLIST("download_recommended_playlist"),
	//New commands here!
	TEST_CATALOG("test_catalog"),
	DISPLAY_ALL("display_all"),
	
	LOGOUT("logout"),
	EXIT("exit"),
	MENU("menu"),

	INVALID("");
	
	private String description;
	
	//Constructor
	ECommands(String description){
		this.description = description;
	}
	
	//Getter
	public String getDescription() {
		return description;
	}
	
	

}



