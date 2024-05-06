package edu.mu.finalproject.view;
import edu.mu.finalproject.controller.AccountController;
import edu.mu.finalproject.model.Account;
import java.util.Scanner;

/**
 * The AccountView class handles receiving input from a user and displaying results of operations 
 * relating to user account management.
 * 
 * It provides methods that allow the user to create a new account or delete their current one,
 * log in, edit their user information, save songs and playlists they like, and follow other users.
 * 
 * Utilizes the methods of the AccountController which uses the AccountSingleton.
 * Methods of AccountView are called by UIExecutionMethods
 */
public class AccountView {
	/**
	 * Utilizes the AccountController class to interact with accounts and JSON file.
	 */
	static AccountController controller = new AccountController();
	
	/**
	 * Attempts to log the user in to an account.
	 * First, it prompts them for their username and password and then uses the AccountController to 
	 * check if there is an account that matches these values. This returns an error code integer.
	 * The integer is passed to displayResult and a different message is displayed depending on the 
	 * integer value. 
	 * 
	 * @return The account of the user if log in is successful, or null if it is not.
	 */
	public static Account viewLogin() {
        String usernameMessage = "Enter username: ";
        String passwordMessage = "Enter password: ";
        
        String username = getStringInput(usernameMessage);
        String password = getStringInput(passwordMessage);
        
        
        Account account = controller.loginUser(username, password);
        if (account == null) {
        	System.out.println("Login unsuccessful. Please try again.");
        }
        else {
        	System.out.println("Welcome back " + account.getUsername() + "!");
        }

        return account;
        
	}
	
	/**
	 * Attempts to create an account for the user.
	 * First, it prompts them for their desired username and password and then uses the AccountController to
	 * create their account, ensuring the username is not taken in the process. The controller returns an
	 * error code integer, which is passed to displayResult, which displays a different message depending
	 * on the integer.
	 * 
	 * @return The newly created account if it is successfully created, or null if it is not.
	 */
	public static Account viewCreateAccount() {
        String usernameMessage = "Enter desired username: ";
        String passwordMessage = "Enter desired password: ";
        
        String username = getStringInput(usernameMessage);
        String password = getStringInput(passwordMessage);
        
        Account account = controller.createAccount(username, password);
        if (account == null) {
        	System.out.println("Username already taken. Could not create account.");
        }
        else {
        	System.out.println("Welcome " + account.getUsername() + "! Your account was successfully created.");
        }
        return account;
	}
	
	/**
	 * Attempts to delete a user account from the accounts List in the AccountSingleton.
	 * This method will only be called by a logged in user, and UserInterface will pass in the
	 * account of the user currently logged in. Then, the AccountController will delete their Account 
	 * from the AccountSingleton List of accounts, and return an error code. displayResult is then called,
	 * which displays a different message depending on the error code. 
	 * 
	 * @param account The Account to be deleted from the system.
	 * @return An error code ( 0 on success, and 1 on failure) for the deletion.
	 */
	public static int viewDeleteAccount(Account account) {
		String username = account.getUsername();
		int isDeleted = controller.deleteAccount(username);
		
		String successMessage = "Account successfully deleted.";
		String failureMessage = "Account cannot be deleted.";
		
		return displayResult(isDeleted, successMessage, failureMessage, "", "");
	}
	
	/**
	 * Attempts to follow another user account. First, gets input from the user on 
	 * who they would like to follow. Then, the AccountController adds that user to their list
	 * of followedUsers, and returns an error code. displayResult displays a different message
	 * to the user depending on the value of the error code. 
	 * 
	 * @param account Account of the user trying to follow someone.
	 * @return An error code (0 on success, 1, -1, or -2 on failure).
	 */
	public static int viewFollowUser(Account account) {
		
		String userToFollowMessage = "Who would you like to follow? Enter their username: ";
		String userToFollow = getStringInput(userToFollowMessage);
	    
		int isFollowed = controller.followUser(account.getUsername(), userToFollow);
		
		String successMessage = "Successfully followed " + userToFollow + ".";
		String failureMessage = "Cannot follow user " + userToFollow + ". Please try again.";
		String specialMessage = "You already follow that user!";
		String specialMessage2 = "You cannot follow yourself!";
		
		return displayResult(isFollowed, successMessage, failureMessage, specialMessage, specialMessage2);
	}
	
	/**
	 * Attempts to change the username of the Account of the user that is logged in. First, get
	 * input from the user on what they want their username to be. Then, the AccountController 
	 * attempts to update their username and returns an error code. displayResult displays a 
	 * different message to the user depending on the value of the error code.
	 * 
	 * @param account Account of the user trying to change their username.
	 * @return An error code (0 for success, 1 or -1 for failure).
	 */
	public static int viewChangeUsername(Account account) {
		
        String usernameMessage = "Enter new username: ";
        String username = getStringInput(usernameMessage);
        
        int usernameChanged = controller.changeUsername(username, account.getUsername());
        
        String successMessage = "Good news " + account.getUsername() + "! Your username has been changed.";
		String failureMessage = "Could not update username. Please try again";
		String specialMessage = "Could not change username. Desired username already taken.";
		String specialMessage2 = "That is already your username! Try again";
		
        return displayResult(usernameChanged, successMessage, failureMessage, specialMessage, specialMessage2);
	}
	
	/**
	 * Attempts to change the password of the Account of the user that is logged in. First, get
	 * input from the user on what they want their password to be. Then, the AccountController 
	 * attempts to update their password and returns an error code. displayResult displays a 
	 * different message to the user depending on the value of the error code.
	 * 
	 * @param account Account of the user trying to change their password.
	 * @return An error code (0 for success, 1 or for failure).
	 */
	public static int viewChangePassword(Account account) {
		String passwordMessage = "Enter new password: ";
		String password = getStringInput(passwordMessage);
		
		int passwordChanged = controller.changePassword(account.getUsername(), account.getPassword(), password);
		
		String successMessage = "Good news " + account.getUsername() + "! Your password has been changed.";
		String failureMessage = "Could not change password. Please try again.";
		String specialMessage = "Password same. Could not be changed.";
		
        return displayResult(passwordChanged, successMessage, failureMessage, specialMessage, "");
	}
	
	/** 
	 * Attempts to save a song to the Account of the user that is logged in. First, get
	 * input from the user as to what song they want to save. Then, the AccountController attempts
	 * to save that song to their account and returns an error code. displayResult then displays a
	 * different message to the user depending on the value of the error code.
	 * 
	 * @param account Account of the user trying to save a song.
	 * @return An error code (0 for success, 1 or -1 for failure).
	 */
	public static int viewSaveSong(Account account) {
		String songMessage = "Enter song name: ";
		String song = getStringInput(songMessage);
		
		int songSaved = controller.saveSong(song, account.getUsername());
		
		String successMessage = "Song successfully saved to " + account.getUsername() + "'s account.";
		String failureMessage = "Error saving song. Please try again.";
		String specialMessage = "Error saving song. You already have this song saved!";
		
		return displayResult(songSaved, successMessage, failureMessage, specialMessage, "");
	}
	
	/** 
	 * Attempts to save a playlist to the Account of the user that is logged in. First, get
	 * input from the user as to what song they want to save. Then, the AccountController attempts
	 * to save that playlist to their account and returns an error code. displayResult then displays a
	 * different message to the user depending on the value of the error code.
	 * 
	 * @param account Account of the user trying to save a playlist.
	 * @return An error code (0 for success, 1 or -1 for failure).
	 */
	public static int viewSavePlaylist(Account account) {
		String playlistMessage = "Enter playlist name: ";
		String playlist = getStringInput(playlistMessage);
		
		int playlistSaved = controller.savePlaylist(playlist, account.getUsername());
		
		String successMessage = "Playlist successfully saved to " + account.getUsername() + "'s account.";
		String failureMessage = "Error saving playlist. Please try again.";
		String specialMessage = "Error saving playlist. You already have this playlist saved!";
		
		return displayResult(playlistSaved, successMessage, failureMessage, specialMessage, "");
	}
	
	/**
	 * Takes in an integer error code and String messages and displays a different message depending
	 * on the value of the error code. 
	 * 
	 * @param error code Integer that indicates if an operation was successful or not.
	 * @param successMessage Message to display on success.
	 * @param failureMessage Message to display on failure.
	 * @param specialMessage Message to display on special case #1. (varies by operation)
	 * @param specialMessage2 Message to display on special case #2.
	 * @return same error code integer
	 */
	private static int displayResult(int errorCode, String successMessage, String failureMessage, String specialMessage, String specialMessage2) {
		if (errorCode == -1) {
			System.out.println(specialMessage);
		}
		else if (errorCode == 1) {
			System.out.println(failureMessage);
		}
		else if (errorCode == -2) {
			System.out.println(specialMessage2);
		}
		else {
			System.out.println(successMessage);
		}
		return errorCode;
	}
	/**
	 * Takes in a message and displays it to the user. Then prompts them for input and continues 
	 * displaying the message until they enter a non-empty input.
	 * 
	 * @param message Message indicating to the user the requested input.
	 * @return String representing user input.
	 */
	private static String getStringInput(String message) {
		Scanner scanner = new Scanner(System.in);
	    String string;
	        
	    // Keep prompting the user until non-empty input is entered
	    do {
            System.out.println(message);
            string = scanner.nextLine().trim();

            if (string.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
	    }
	    while (string.isEmpty());
	    return string;
	}
	
}
