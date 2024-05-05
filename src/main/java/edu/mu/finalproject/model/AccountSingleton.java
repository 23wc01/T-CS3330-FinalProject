package edu.mu.finalproject.model;
import java.util.List;

/**
 * Singleton class responsible for managing a single instance of account data.
 */
public class AccountSingleton {

	/**
	 * The single instance of the AccountSingleton class.
	 */
	private static AccountSingleton instance;
	
	/**
	 * The list of accounts managed by the singleton instance.
	 */
	public static List<Account> accounts;
	
	/**
	 * Private constructor to prevent instantiation from outside class.
	 */
	private AccountSingleton() {
	}
	
	/**
	 * Returns the single instance of the AccountSingleton class.
	 * 
	 * @return The instance of the AccountSingleton class.
	 */
	public static AccountSingleton getInstance() {
		
		if (instance == null) {
			instance = new AccountSingleton();
			accounts = AccountFileReader.loadAccounts();
		}
		
		return instance;
	}
	
	/** 
	 * Retrieves the list of accounts managed by the singleton instance.
	 * 
	 * @return The list of accounts.
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Sets the list of accounts to be managed by the singleton instance.
	 * 
	 * @param accounts The list of accounts to be set.
	 */
	public void setAccounts(List<Account> accounts) {
		AccountSingleton.accounts = accounts;
	}

}
