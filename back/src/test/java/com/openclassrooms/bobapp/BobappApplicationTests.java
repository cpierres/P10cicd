package com.openclassrooms.bobapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class BobappApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void workflowFailureTest() {
		// Assertion qui échouera toujours
		fail("Ce test échoue intentionnellement pour tester le workflow");
	}

}
