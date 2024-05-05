package downloadPlaylistTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.util.downloadPlaylistBuilder.AccountHtmlBuilder;
import edu.mu.finalproject.util.downloadPlaylistBuilder.HtmlDirector;

class HtmlBuilderTest {
	private static AccountHtmlBuilder accountHtmlBuilder;
	private static Account account;
	private static HtmlDirector htmlDirector;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		account = new Account(0, "test", "test");
		accountHtmlBuilder = new AccountHtmlBuilder(account);
		htmlDirector = new HtmlDirector();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testSetReplaceHtml() {
		ArrayList<String> modelFields = new ArrayList<String>();
		modelFields.add("test data");
		assertTrue(accountHtmlBuilder.setReplaceHtml(modelFields));

		assertFalse(accountHtmlBuilder.setReplaceHtml(null));
	}
	@Test 
	void testSetTemplateFileLocation() {
		accountHtmlBuilder = new AccountHtmlBuilder(account);

		assertTrue(accountHtmlBuilder.setTemplateFileLocation("outputs"));
		assertFalse(accountHtmlBuilder.setTemplateFileLocation(null));
		assertTrue(accountHtmlBuilder.setTemplateFileLocation(""));
	}
	@Test
	void testSetHtmlMapModelFields() {
		assertEquals(null, htmlDirector.constructAccountHtml(new Account(0, null, null)));
		assertTrue(accountHtmlBuilder.setHtmlMapModelFields(new HashMap<String, String>()));
		assertFalse(accountHtmlBuilder.setHtmlMapModelFields(null));

		accountHtmlBuilder = new AccountHtmlBuilder(null);
		
		accountHtmlBuilder = new AccountHtmlBuilder(new String());
	}

	
}
