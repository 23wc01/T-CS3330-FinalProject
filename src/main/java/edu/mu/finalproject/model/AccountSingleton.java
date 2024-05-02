package edu.mu.finalproject.model;
import java.util.List;

public class AccountSingleton {

	private static AccountSingleton instance;
	public static AccountFileReader freader = new AccountFileReader();;
	public static List<Account> accounts = freader.loadAccounts();
	
	private AccountSingleton() {
	}
	
	public static AccountSingleton getInstance() {
		
		if (instance == null) {
			instance = new AccountSingleton();
		}
		
		return instance;
	}

	public static List<Account> getAccounts() {
		return accounts;
	}

	public static void setAccounts(List<Account> accounts) {
		AccountSingleton.accounts = accounts;
	}

}
