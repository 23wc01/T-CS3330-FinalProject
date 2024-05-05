package setupPreferenceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.Preference;
import edu.mu.finalproject.util.ManualSetupPreferenceStrategy;
import edu.mu.finalproject.util.SetupPreferenceContext;
import edu.mu.finalproject.view.SetupPreferenceView;

class SetupPreferenceContextTest {
	private static SetupPreferenceContext setupPreferenceContext;
	private static SetupPreferenceView setupPreferenceView;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupPreferenceContext = new SetupPreferenceContext(new ManualSetupPreferenceStrategy());
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
	void testUpdatePreference() {
		assertNull(setupPreferenceContext.updatePreference(null));
		assertEquals(Preference.RAP, setupPreferenceContext.updatePreference(setupPreferenceView));
	}

}
