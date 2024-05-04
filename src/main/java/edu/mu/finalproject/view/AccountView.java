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
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        
        // Keep prompting the user until both username and password are provided
        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();
        } while (username.isEmpty());

        do {
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();
        } while (password.isEmpty());

        scanner.close();
        
        Account account = controller.loginUser(username, password);
        if (account == null) {
        	System.out.println("Login unsuccessful. Please try again.");
        }
        else {
        	System.out.println("Welcome " + account.getUsername() + "!");
        }

        return account;
        
	}
	
	public static Account viewCreateAccount() {
		Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        boolean isUsernameTaken = false;

        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty.");
                continue;
            }

            isUsernameTaken = controller.isUsernameTaken(username);
            if (isUsernameTaken) {
                System.out.println("Username is already taken. Please choose another one.");
            }
        } while (username.isEmpty() || isUsernameTaken);

        do {
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty.");
            }
        } while (password.isEmpty());
        
        scanner.close();
        
        Account account = controller.createAccount(username, password);
        if (account == null) {
        	System.out.println("Account creation unsuccessful.");
        }
        else {
        	System.out.println("Welcome " + account.getUsername() + "! Your account was successfully created.");
        }
        return account;
	}
	
	public static int viewDeleteAccount(Account account) {
		String username = account.getUsername();
		int isDeleted = controller.deleteAccount(username);
		if (isDeleted == 1) {
			System.out.println("Account cannot be deleted.");
		}
		else {
			System.out.println("Account successfully deleted.");
		}
		return isDeleted;
	}
	
	public static int viewFollowUser() {
		return 0;
	}
	
	public static int viewChangeUsername() {
		return 0;
	}
	
	public static int viewChangePassword() {
		return 0;
	}
	
	public static int viewSaveSong() {
		return 0;
	}
	
	public static int viewSavePlaylist() {
		return 0;
	}
}
