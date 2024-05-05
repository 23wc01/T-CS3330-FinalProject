package setupPreferenceTests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.mockito.Mockito.*;


import edu.mu.finalproject.controller.SetupPreferenceController;
import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
@TestMethodOrder(OrderAnnotation.class)
class SetupPreferenceControllerTest {
	private static SetupPreferenceController setupPreferenceController;
	private static Account account;
	private static final InputStream SYSTEM_IN = System.in;
	private static SetupPreferenceContext setupPreferenceContext;
	private static PipedInputStream inputStream;
    private static PipedOutputStream outputStream;
    private Scanner scanner;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupPreferenceController = new SetupPreferenceController();
		account = new Account(0, "test", "test");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//System.setIn(SYSTEM_IN);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		setupPreferenceContext = null;
	}
	
	@Test
	@Order(1)
	void testSetContextQuiz()
	{
		setupPreferenceContext = new SetupPreferenceContext(new QuizSetupPreferenceStrategy());
		assertEquals(setupPreferenceContext.getClass(), setupPreferenceController.setContext(0).getClass());
	}
	@Test
	@Order(2)
	void testSetContextManual() {
		setupPreferenceContext = new SetupPreferenceContext(new ManualSetupPreferenceStrategy());
		assertEquals(setupPreferenceContext.getClass(), setupPreferenceController.setContext(1).getClass());
	}
	@Test 
	@Order(3)
	void testSetContextFail() {
		assertEquals(null, setupPreferenceController.setContext(65));
	}
	
	@Test
	@Order(4)
	void testNewPreference() {
		assertFalse(setupPreferenceController.newPreference(null));
	}
	
	
	@Test
	@Order(5)
	void testNewPreferenceManual() { // Success case
		System.err.println("\n!!!! Please test by entering 1, 1 into console!!!!!\n");
		assertTrue(setupPreferenceController.newPreference(account));
		assertEquals(Preference.RAP, account.getUserPreference());
	}
	
	@Test
	@Order(6)
	void testNewPreferenceFail() { // Fail case
		System.err.println("\n!!!! Please test by entering 2, 1, 1 into console!!!!!\n");
		assertTrue(setupPreferenceController.newPreference(account));
		assertEquals(Preference.RAP, account.getUserPreference());
	}
	
	@Test
	@Order(7)
	void testNewPreferenceQuiz() { // Success case
		System.err.println("\n!!!! Please test by entering 0, 1, 3, 3, 3 into console!!!!\n");
		assertTrue(setupPreferenceController.newPreference(account));
		assertEquals(Preference.POP, account.getUserPreference());
	}
	/*@Test
	void testNewPreferenceMock() {
		MockInputSteam mockInputSteam = new MockInputSteam();
		System.setIn("1\n");
		
	}
}
*/
}
