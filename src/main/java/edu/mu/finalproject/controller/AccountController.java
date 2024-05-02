package edu.mu.finalproject.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.AccountSingleton;
import edu.mu.finalproject.model.AccountFileReader;

public class AccountController {
	
	static AccountSingleton theAccountSingleton = AccountSingleton.getInstance();
	private List<Account> accounts;				// List of all application accounts
	private int accountCounter;					// Increments with creation of each new account
	
	
	/**
	 *  Constructs a new AccountController instance.
	 *  Initializes the account list and sets the value for the account counter.
	 */
	public AccountController() {
		this.accounts = theAccountSingleton.getAccounts();
		this.accountCounter = getLastAccountID() + 1;
	}
	
	/**
	 * Attempts to log in a user with the provided username and password. 
	 * The password is hashed before comparing it to the stored passwords.
	 * 
	 * @param username The username of the user attempting to log in. 
	 * @param password The plaintext password of the user attempting to log in.
	 * @return The Account object associated with the provided parameters or null 
	 * 		   there is no object associated with these.
	 */
	public Account loginUser(String username, String password) {
		// Hash the password
		String hashedPassword = hashPassword(password);
		// Find account asscociated with username and password
		for (Account account : this.accounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(hashedPassword)) {
				return account;
			}
		}
		return null; 
	}
	
	/**
	 * Attempts to create a new user account with the provided username and password.
	 * 
	 * @param username The desired username for the new account.
	 * @param password The plaintext password for the new account.
	 * @return The newly created Account object or null if the username is
	 * 		   already taken.
	 */
	public Account createAccount(String username, String password) {
		// Check if the username already in use
		if (isUsernameTaken(username) == true) {
			return null;
		}
		// Create an account object and add it to the accounts list
		Account account = new Account(this.accountCounter, username, password);
		this.accountCounter++; 		
		this.accounts.add(account);
		saveChanges();
		return account;
	}
	
	/**
	 * Deletes the user account associated with the provided username if it exists.
	 * 
	 * @param username The username of the account to be deleted.
	 * @return 0 if the account is successfully deleted, or 1 if the account cannot be deleted.
	 */
	public int deleteAccount(String username) {
		for (int i = 0; i < this.accounts.size(); i++) {
			if (this.accounts.get(i).getUsername().equals(username)) {
				this.accounts.remove(i);
				return saveChanges();
			}
		}
		return 1;
	}
	
	
	/**
	 * Retrieves the ID of the most recently created account.
	 * 
	 * @return The ID of the most recently created account, or 0
	 * 		   if the list of accounts is empty.
	 */
	private int getLastAccountID() {
		// Check if the accounts list is empty.
		if (accounts.size() == 0) {
			return 0;
		}
		// Get the index of the last account in the list and return its ID.
		int lastAccountIndex = accounts.size()-1;
		return accounts.get(lastAccountIndex).getAccountID();
	}
	
	
	/**
	 * Hashes the provided password using the MD5 hash function to protect user passwords.
	 * 
	 * @param passwordToHash The plain text password to be hashed.
	 * @return The hashed password as a hexadecimal string or null if an error occurred.
	 */
	private String hashPassword(String passwordToHash) {
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hashedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		return hashedPassword;
	}
	
	/**
	 * Checks if provided username is already taken by an existing user.
	 * 
	 * @param username The username to check for availability.
	 * @return true if username is already taken, false otherwise.
	 */
	public boolean isUsernameTaken(String username) {
		for (Account account: this.accounts) {
			if (account.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Saves the provided song to the list of saved songs for the user with
	 * the provided username.
	 * 
	 * @param song The name of the song to be saved.
	 * @param username The username of the user to save the song for.
	 * @return 0 if the song is successfully saved, or 1 if it is not.
	 */
	public int saveSong(String song, String username) {
		// Find the user account with the specified username.
		for (Account account : accounts) {
			if (account.getUsername().equals(username)) {
				// Add the song to the user's list of saved songs
				List<String> update = account.getSavedSongs();
				update.add(song);
				account.setSavedSongs(update);
				return saveChanges();
			}
		}
		return 1;
	}
	
	/**
	 * Saves the provided playlist to the list of saved playlists for the user
	 * with the provided username.
	 * 
	 * @param playlist The name of the playlist to be saved.
	 * @param username The username of the user to save the playlist for.
	 * @return 0 if the playlist is successfully saved, or 1 if it is not.
	 */
	public int savePlaylist(String playlist, String username) {
		// Find the user account with the specified username.
		for (Account account : accounts) {
			if (account.getUsername().equals(username)) {
				// Add the playlist to the user's list of saved playlists.
				List<String> update = account.getSavedPlaylists();
				update.add(playlist);
				account.setSavedPlaylists(update);
				return saveChanges();
			}
		}
		return 1;
	}
	
	/**
	 * Adds the specified user to the list of followed users for the user
	 * with the provided username. 
	 * 
	 * @param username The username of the user who wants to follow someone.
	 * @param userToFollow The username of the user to be followed.
	 * @return 0 if the user is successfully followed, or 1 if they are not.
	 */
	public int followUser(String username, String userToFollow) {
		// Make sure username of person to follow exists.
		if (isUsernameTaken(userToFollow) == false) {
			return 1;
		}
		else {
			// Add the new user to the list of followed users.
			for (Account account : accounts) {
				if (account.getUsername().equals(username)) {
					List<String> update = account.getFollowedUsers();
					update.add(userToFollow);
					account.setFollowedUsers(update);
					return saveChanges();
				}
			}
		}
		return 1;
	}
	
	/**
	 * Changes the username of the user with the specified old username to the
	 * new username. 
	 * 
	 * @param newUsername The username to be set.
	 * @param oldUsername The username to be changed.
	 * @return 0 if the username is successfully changed, or 1 if it is not.
	 */
	public int changeUsername(String newUsername, String oldUsername) {
		// Check if desired username is already taken.
		if (isUsernameTaken(newUsername)) {
			return 1;
		}
		// Find the correct account and change its username. 
		for (Account account : accounts) {
			if (account.getUsername().equals(oldUsername)) {
				account.setUsername(newUsername);
				return saveChanges();
			}
		}
		return 1;
	}
	
	/**
	 * Changes the password of the user with the specified username from the
	 * old password to the new one.
	 * 
	 * @param username The username of the user who wants to change their password.
	 * @param oldPassword The old password of the user.
	 * @param newPassword The new password of the user.
	 * @return 0 if the password is successfully changed, 1 if it is not.
	 */
	public int changePassword(String username, String oldPassword, String newPassword) {
		String hashedOldPassword = hashPassword(oldPassword);
		String hashedNewPassword = hashPassword(newPassword);
		for (Account account : accounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(hashedOldPassword)) {
				account.setPassword(hashedNewPassword);
				return saveChanges();
			}
		}
		return 1;
	}
	
	/**
	 * Resets the list of accounts to the state stored in the AccountSingleton instance.
	 * 
	 * @return 0 if the accounts are successfully reset. 
	 */
	public int reset() {
		this.accounts = theAccountSingleton.getAccounts();
		return 0;
	}
	
	/**
	 * Saves the current state of the list of accounts to the singleton and
	 * external storage. 
	 * 
	 * @return 0 if the changes are successfully saved, or 1 if there is an error.
	 */
	public int saveChanges() {
		try {
			AccountFileReader.saveAccounts(accounts);
			theAccountSingleton.setAccounts(accounts);
			return 0;	
		} catch (Exception e) {
			return 1;
		}
	}
	
}
