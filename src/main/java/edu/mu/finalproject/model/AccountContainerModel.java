package edu.mu.finalproject.model;

import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// This class manages the account information stored in the account JSON file.
// It is used to create new accounts, load in the accounts, and to append or delete accounts.
public class AccountContainerModel {
	
	// Store the file path to the JSON file and a List of all accounts
	static String path = "files/accounts.json";
	private List<Account> activeAccounts;

	public AccountContainerModel() {
		this.activeAccounts = new ArrayList<>();
		loadAccounts();
	}
	
	public List<Account> loadAccounts() {
		return activeAccounts;
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
	
	public int createAccount() {
		
		return 0;
	}
	
	public int deleteAccount(String username) {
		
		return 0; //on success
	}
	
	public int saveChanges() {
		
		return 0;
	}
	

}
