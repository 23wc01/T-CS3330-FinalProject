package downloadPlaylistTests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.Account;
import edu.mu.finalproject.util.downloadPlaylistBuilder.AccountHtmlBuilder;

class HtmlBuilderTest {
	private static AccountHtmlBuilder accountHtmlBuilder;
	private static Account account;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		account = new Account(0, "test", "test");
		accountHtmlBuilder = new AccountHtmlBuilder(account);
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
	void testSetHtmlMapModelFields() {
		assertTrue(accountHtmlBuilder.setHtmlMapModelFields(new HashMap<String, String>()));
		assertFalse(accountHtmlBuilder.setHtmlMapModelFields(null));
		assertFalse(accountHtmlBuilder.setReplaceHtml(null));
		assertFalse(accountHtmlBuilder.setTemplateFileLocation(null));
		assertFalse(accountHtmlBuilder.setTemplateFileLocation(""));

		accountHtmlBuilder = new AccountHtmlBuilder(null);

		accountHtmlBuilder = new AccountHtmlBuilder(new String());

	}

}
