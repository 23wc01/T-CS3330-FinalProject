package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class LoginTest {
    
	private AccountController loginController = new AccountController();
    private List<Account> accounts = new ArrayList<Account>();
    private List<Account> nullAccountList = null;
    
    @Before
    public void setUp() {
        // Creates some accounts for testing and add them to the accounts List
    	String hashedPassword1 = loginController.hashPassword("Unicorn");
    	String hashedPassword2 = loginController.hashPassword("Dragon");
        Account account1 = new Account(1, "Alyssa", hashedPassword1);
        Account account2 = new Account(2, "Evie", hashedPassword2);
        accounts.add(account1);
        accounts.add(account2);
    	loginController.accounts = accounts;

    }
    
    @Test
    public void testValidLogin() {
    	String validHashedPassword = loginController.hashPassword("Unicorn");
        Account validAccount = new Account(1, "Alyssa", validHashedPassword);
        assertEquals(validAccount, loginController.loginUser("Alyssa", "Unicorn"));
    }
    
    @Test
    public void testInvalidUsername() {
        assertNull(loginController.loginUser("Wen-Hsin", "Dragon"));
    }
    
    @Test
    public void testInvalidPassword() {
        assertNull(loginController.loginUser("Evie", "Cat"));
    }
    
    @Test
    public void testAccountsNull() {
    	loginController.accounts = nullAccountList;
    	assertNull(loginController.loginUser("Alyssa", "Unicorn"));
    }
    
}
