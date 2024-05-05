package modelsTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.finalproject.model.ECommands;

class ECommandsTest {
	private static ECommands eCommand;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		eCommand = ECommands.DISPLAY_ALL;
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
	void testGetDescription() {
		assertEquals("display_all", eCommand.getDescription());
	}

}
