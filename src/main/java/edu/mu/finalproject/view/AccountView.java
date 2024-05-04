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

	static AccountController controller = new AccountController();
	
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
	
	public static int viewDeleteAccount(Account account) {
		String username = account.getUsername();
		int isDeleted = controller.deleteAccount(username);
		
		String successMessage = "Account successfully deleted.";
		String failureMessage = "Account cannot be deleted.";
		
		return displayResult(isDeleted, successMessage, failureMessage, "");
	}
	
	public static int viewFollowUser(Account account) {
		
		String userToFollowMessage = "Who would you like to follow? Enter their username: ";
		String userToFollow = getStringInput(userToFollowMessage);
	    
		int isFollowed = controller.followUser(account.getUsername(), userToFollow);
		
		String successMessage = "Successfully followed " + userToFollow + ".";
		String failureMessage = "Cannot follow user " + userToFollow + ". Please try again.";
		String specialMessage = "You already follow that user!";
		
		return displayResult(isFollowed, successMessage, failureMessage, specialMessage);
	}
	
	public static int viewChangeUsername(Account account) {
		
        String usernameMessage = "Enter new username: ";
        String username = getStringInput(usernameMessage);
        
        int usernameChanged = controller.changeUsername(account.getUsername(), username);
        
        String successMessage = "Good news " + account.getUsername() + "! Your username has been changed.";
		String failureMessage = "Could not update username. Please try again";
		String specialMessage = "Could not change username. Desired username already taken.";
		
        return displayResult(usernameChanged, successMessage, failureMessage, specialMessage);
	}
	
	public static int viewChangePassword(Account account) {
		String passwordMessage = "Enter new password: ";
		String password = getStringInput(passwordMessage);
		
		int passwordChanged = controller.changePassword(account.getUsername(), account.getPassword(), password);
		
		String successMessage = "Good news " + account.getPassword() + "! Your password has been changed.";
		String failureMessage = "Could not change password. Please try again.";
		
        return displayResult(passwordChanged, successMessage, failureMessage, "");
	}
	
	public static int viewSaveSong(Account account, String song) {
		int songSaved = controller.saveSong(song, account.getUsername());
		
		String successMessage = "Song successfully saved to " + account.getUsername() + "'s account.";
		String failureMessage = "Error saving song. Please try again.";
		
		return displayResult(songSaved, successMessage, failureMessage, "");
	}
	
	public static int viewSavePlaylist(Account account, String playlist) {
		int playlistSaved = controller.savePlaylist(playlist, account.getUsername());
		
		String successMessage = "Playlist successfully saved to " + account.getUsername() + "'s account.";
		String failureMessage = "Error saving playlist. Please try again.";
		
		return displayResult(playlistSaved, successMessage, failureMessage, "");
	}
	
	private static int displayResult(int errorCode, String successMessage, String failureMessage, String specialMessage) {
		if (errorCode == -1) {
			System.out.println(specialMessage);
		}
		else if (errorCode == 1) {
			System.out.println(failureMessage);
		}
		else {
			System.out.println(successMessage);
		}
		return errorCode;
	}
	
	private static String getStringInput(String message) {
		Scanner scanner = new Scanner(System.in);
	    String string;
	        
	    // Keep prompting the user until non-empty input is entered
	    do {
            System.out.print(message);
            string = scanner.nextLine().trim();

            if (string.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
	    }
	    while (string.isEmpty() == true);
	    scanner.close();
	    return string;
	}
	
}
