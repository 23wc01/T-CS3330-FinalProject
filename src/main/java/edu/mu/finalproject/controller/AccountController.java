package edu.mu.finalproject.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.AccountSingleton;
import edu.mu.finalproject.model.AccountFileReader;
import edu.mu.finalproject.model.Preference;

public class AccountController {
	
	private List<Account> accounts;				// List of all application accounts
	private int accountCounter;					// Increments with creation of each new account
	
	
	// CONSTRUCTOR 
	public AccountController() {
		AccountSingleton.getInstance();
		this.accounts = AccountSingleton.getAccounts();
		this.accountCounter = getLastAccountID(this.accounts) + 1;
	}
	
	// This method is used by the login controller to login a user.
	// If the user successfully logs in, it returns their account object, otherwise it returns null.
	public Account loginUser(String username, String password) {
		String hashedPassword = hashPassword(password);
		for (Account account : this.accounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(hashedPassword)) {
				return account;
			}
		}
		return null; 
	}
	
	// This method adds a new account to the accounts List to register a new user.
	public Account createAccount(String username, String password) {
		if (isUsernameTaken(username) == true) {
			return null;
		}
		Account account = new Account(this.accountCounter, username, password);
		this.accountCounter++; 		
		this.accounts.add(account);
		return account;
	}
	
	
	// This method returns the account ID of the most recently created account.
	// If there have not been any accounts created, it returns 0.
	private int getLastAccountID(List<Account> accounts) {
		if (accounts.size() == 0) {
			return 0;
		}
		int lastAccountIndex = accounts.size()-1;
		return accounts.get(lastAccountIndex).getAccountID();
	}
	
	
	// This method uses a hash function to protect the user passwords
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
	
	// This method checks if a user name is already taken
	public boolean isUsernameTaken(String username) {
		for (Account account: this.accounts) {
			if (account.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public int saveChanges() {
		AccountSingleton.freader.saveAccounts(this.accounts);
		return 0;
	}

}
