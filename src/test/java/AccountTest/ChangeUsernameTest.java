package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class ChangeUsernameTest {

	private AccountController accountController = new AccountController();
	private List<Account> accounts = new ArrayList<Account>();
	
	// This is the same method as in AccountController but without saveChanges() so the file and AccountSingleton are not affected. 
	public int changeUsername(String newUsername, String oldUsername) {
		// Check if desired username is already taken.
		if (newUsername.equals(oldUsername)) {
			return -2;
		}
		if (accountController.isUsernameTaken(newUsername)) {
			return -1;
		}
		// Find the correct account and change its username. 
		for (Account account : accounts) {
			if (account.getUsername().equals(oldUsername)) {
				account.setUsername(newUsername);
				return 0;
			}
		}
		return 1;
	}
	
	@Before
    public void setUp() {
		// Creates some accounts for testing and add them to the accounts List
    	String hashedPassword1 = accountController.hashPassword("Unicorn");
    	String hashedPassword2 = accountController.hashPassword("Dragon");
        Account account1 = new Account(1, "Alyssa", hashedPassword1);
        Account account2 = new Account(2, "Evie", hashedPassword2);
        accounts.add(account1);
        accounts.add(account2);
        accountController.accounts = accounts;

    }
	
	@Test
	public void testValidChange() {
		assertEquals(0, changeUsername("Markiplier", "Alyssa"));
	}
	
	@Test
	public void testAccountDoesNotExist() {
		assertEquals(1, changeUsername("Markiplier", "Brooke"));
	}
	
	@Test
	public void testNewUsernameSameAsOld() {
		assertEquals(-2, changeUsername("Alyssa", "Alyssa"));
	}
	
	@Test
	public void testNewUsernameTaken() {
		assertEquals(-1, changeUsername("Evie", "Alyssa"));
	}

}