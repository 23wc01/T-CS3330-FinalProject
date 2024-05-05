package setupPreferenceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.view.SetupPreferenceView;

class SetupPreferenceViewTest {
	private static SetupPreferenceView setupPreferenceView;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupPreferenceView = new SetupPreferenceView();
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
	void testDisplayChoices() {
		assertFalse(setupPreferenceView.displayChoices(null));
	}
	@Test
	void testDisplayIntro() {
		assertFalse(setupPreferenceView.displayIntro(null));
	}
	@Test
	void testDisplayQuestion() {
		assertFalse(setupPreferenceView.displayQuestion(null));
	}
	@Test
	void testDisplayPreference() {
		assertTrue(setupPreferenceView.displayPreference("test preference"));
		assertFalse(setupPreferenceView.displayPreference(null));
	}
	
	@Test 
	void testDisplayQuizError() {
		assertTrue(setupPreferenceView.displayQuizError("test"));
		assertFalse(setupPreferenceView.displayQuizError(null));
	}

}
