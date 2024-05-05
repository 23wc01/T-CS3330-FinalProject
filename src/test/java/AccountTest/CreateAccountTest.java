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
    	Account newAccount = accountController.createAccount("Wen-Hsin", "Pheonix");
    	for (Account account : accountController.accounts) {
    		if (newAccount.getUsername().equals(account.getUsername())) {
    			assertEquals(newAccount, account);
    		}
    	}
    }
    
    @Test
    public void testUsernameIsTaken() {
    	assertNull(accountController.createAccount("Alyssa", "Cat"));
    }

}
