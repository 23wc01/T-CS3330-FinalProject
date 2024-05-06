package AccountTest;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.controller.AccountController;

public class FollowUserTest {

	private AccountController accountController = new AccountController();
	private List<Account> accounts = new ArrayList<Account>();

	
	// This is the same method as in AccountController but without saveChanges() so the file and AccountSingleton are not affected. 
	public int followUser(String username, String userToFollow) {
		// Make sure user does not follow themselves!
		if (username.equals(userToFollow)) {
			return -2;
		}
		// Make sure username of person to follow exists.
		if (accountController.isUsernameTaken(userToFollow) == false) {
			return 1;
		}
		else {
			// Add the new user to the list of followed users.
			for (Account account : accountController.accounts) {
				if (account.getUsername().equals(username)) {
					List<String> update = account.getFollowedUsers();
					// Check if the user already follows this person
					for (String user : update) {
						if (user.equals(userToFollow)) {
							return -1;
						}
					}
					// Otherwise add them to the follow list
					update.add(userToFollow);
					account.setFollowedUsers(update);
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
	public void testValidFollow() {
		assertEquals(0, followUser("Alyssa", "Evie"));
	}
	
	@Test
	public void testFollowFakeUser() {
		accountController.accounts = null;
		assertEquals(1, followUser("Alyssa", "Wen-Hsin"));
	}
	
	@Test
	public void testFollowYourself() {
		assertEquals(-2, followUser("Alyssa", "Alyssa"));
	}
	
	@Test
	public void testFollowSomeoneAlreadyFollowed() {
		followUser("Alyssa", "Evie");
		assertEquals(-1, followUser("Alyssa", "Evie"));
	}

}