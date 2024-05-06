package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class CreateAccountTest {
    
	private AccountController accountController = new AccountController();
    private List<Account> accounts = new ArrayList<Account>();
    private Account account1;
    private Account account2;
    
	// This is the same method as in AccountController but without saveChanges() so the file and AccountSingleton are not affected. 
    public Account createAccount(String username, String password) {
		// Check if the username already in use
		if (accountController.isUsernameTaken(username) == true) {
			return null;
		}
		
		// Hash password before sending to account constructor
		String hashedPassword = accountController.hashPassword(password);
				
		// Create an account object and add it to the accounts list
		Account account = new Account(accountController.accountCounter, username, hashedPassword);
		accountController.accountCounter++; 		
		if (accountController.accounts == null) {
			List<Account> accountInit = new ArrayList<Account>();
			accountController.accounts = accountInit;
		}
		accountController.accounts.add(account);
		return account;
	}
    
    @Before
    public void setUp() {
        // Initialize accounts for testing and add them to the accounts List
    	String hashedPassword1 = accountController.hashPassword("Unicorn");
    	String hashedPassword2 = accountController.hashPassword("Dragon");
        account1 = new Account(1, "Alyssa", hashedPassword1);
        account2 = new Account(2, "Evie", hashedPassword2);
        accounts.add(account1);
        accounts.add(account2);
    	accountController.accounts = accounts;
    }
    
    @Test
    public void testValidInput() {
    	Account newAccount = createAccount("Wen-Hsin", "Pheonix");
    	for (Account account : accountController.accounts) {
    		if (newAccount.getUsername().equals(account.getUsername())) {
    			assertEquals(newAccount, account);
    		}
    	}
    }
    
    @Test
    public void testUsernameIsTaken() {
    	assertNull(createAccount("Alyssa", "Cat"));
    }

}
