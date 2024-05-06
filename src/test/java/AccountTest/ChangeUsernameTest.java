package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

class ChangeUsernameTest {

	private AccountController accountController = new AccountController();
	private List<Account> accounts = new ArrayList<Account>();
	
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
		assertEquals(0, accountController.changeUsername("Markiplier", "Alyssa"));
	}
	
	@Test
	public void testAccountDoesNotExist() {
		assertEquals(1, accountController.changeUsername("Markiplier", "Brooke"));
	}
	
	@Test
	public void testNewUsernameSameAsOld() {
		assertEquals(-2, accountController.changeUsername("Alyssa", "Alyssa"));
	}
	
	@Test
	public void testNewUsernameTaken() {
		assertEquals(-1, accountController.changeUsername("Evie", "Alyssa"));
	}

}