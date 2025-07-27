package com.openclassrooms.bobapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

class BackendFailingTest {

    @Test
    void testThatIntentionallyFails() {
        // Ce test échouera toujours
        fail("Ce test backend échoue intentionnellement pour tester le workflow GitHub Actions");
    }
}