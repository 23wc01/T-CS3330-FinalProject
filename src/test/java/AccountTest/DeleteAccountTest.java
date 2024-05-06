package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class DeleteAccountTest {

	private AccountController accountController = new AccountController();
	private List<Account> accounts = new ArrayList<Account>();

	
	// This is the same method as in AccountController but without saveChanges() so the file and AccountSingleton are not affected. 
	public int deleteAccount(String username) {
		if (accountController.accounts != null) {
			for (int i = 0; i < accountController.accounts.size(); i++) {
				if (accountController.accounts.get(i).getUsername().equals(username)) {
					accountController.accounts.remove(i);
					return 0;
				}
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
	public void testValidDelete() {
		assertEquals(0, deleteAccount("Alyssa"));
	}
	
	@Test
	public void testDeleteAccountsNull() {
		accountController.accounts = null;
		assertEquals(1, deleteAccount("Alyssa"));
	}
	
	@Test
	public void testDeleteNonexistentAccount() {
		assertEquals(1, deleteAccount("Thomas"));
	}

}
