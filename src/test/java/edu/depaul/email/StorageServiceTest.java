package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class StorageServiceTest {

    @Test
    @DisplayName("this is the test to see if ss is an instance of StorageService")
    void testSetupStorage() {
        StorageService ss = new StorageService();
        assertTrue(ss instanceof StorageService);
    }
}