package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class ChangePasswordTest {

	private AccountController accountController = new AccountController();
	private List<Account> accounts = new ArrayList<Account>();
	
	// This is the same method as in AccountController but without saveChanges() so the file and AccountSingleton are not affected. 
	public int changePassword(String username, String oldPassword, String newPassword) {
		if (oldPassword.equals(newPassword) ) {
			return -1;
		}
		String hashedNewPassword = accountController.hashPassword(newPassword);
		for (Account account : accounts) {
			if (account.getUsername().equals(username) && account.getPassword().equals(oldPassword)) {
				account.setPassword(hashedNewPassword);
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
		String hashedPassword = accountController.hashPassword("Unicorn");
		assertEquals(0, changePassword("Alyssa", hashedPassword, "Coyote"));
	}
	
	@Test
	public void testAccountDoesNotExist() {
		assertEquals(1, changePassword("Thomas", "Cat", "Dog"));
	}
	
	@Test
	public void testNewPasswordSameAsOld() {
		assertEquals(-1, changePassword("Alyssa", "Unicorn", "Unicorn"));
	}
}
