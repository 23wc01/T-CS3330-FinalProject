package setupPreferenceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.PreferenceQuestion;

class PreferenceQuestionTest {
	private static PreferenceQuestion preferenceQuestion;
	private static ArrayList<HashMap<String, String>> choicesToPrefs;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		preferenceQuestion = new PreferenceQuestion();
		choicesToPrefs = new ArrayList<HashMap<String, String>>();
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
	void testSetQuestion() {
		assertEquals(false, preferenceQuestion.setQuestion(null));
	}
	@Test
	void testSetChoiceToPreferences() {
		assertEquals(true, preferenceQuestion.setChoiceToPreferences(choicesToPrefs));
		assertEquals(false, preferenceQuestion.setChoiceToPreferences(null));
	}
	
}
