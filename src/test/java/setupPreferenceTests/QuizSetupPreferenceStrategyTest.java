package setupPreferenceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.model.PreferenceQuestion;
import edu.mu.finalproject.util.QuizSetupPreferenceStrategy;
import edu.mu.finalproject.view.SetupPreferenceView;

class QuizSetupPreferenceStrategyTest {
	private static QuizSetupPreferenceStrategy quizSetupPreferenceStrategy;
	private static PreferenceQuestion preferenceQuestion;
	private static SetupPreferenceView setupPreferenceView; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		quizSetupPreferenceStrategy = new QuizSetupPreferenceStrategy();
		preferenceQuestion = new PreferenceQuestion();
		setupPreferenceView = new SetupPreferenceView(); 

		HashMap<String, String> choiceMapPreference = new HashMap<String, String>();
		choiceMapPreference.put(Preference.RAP.capitalizePreference(), "test choice");
		
		preferenceQuestion.setChoiceToPreferences(new ArrayList<HashMap<String, String>>());
		preferenceQuestion.getChoiceToPreferences().add(choiceMapPreference);
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
	void testRecordScore() {
		assertEquals(false, quizSetupPreferenceStrategy.recordScore(null));
	}

	@Test
	void testScoreQuestion() {
		assertFalse(quizSetupPreferenceStrategy.scoreQuestion(1, null));
		assertFalse(quizSetupPreferenceStrategy.scoreQuestion(0, preferenceQuestion));
		assertTrue(quizSetupPreferenceStrategy.scoreQuestion(1, preferenceQuestion));	
	}
	@Test
	void testSetupPreference() {
		assertEquals(null, quizSetupPreferenceStrategy.setupPreference(null));
		System.err.println("Please enter 1, 3, 3, 3");
		assertEquals(Preference.POP, quizSetupPreferenceStrategy.setupPreference(setupPreferenceView));

	}

}
