package models;

import junit.framework.TestCase;

public class WhaleTest extends TestCase {
    Whale whale;

    public void setUp() throws Exception {
        whale = new Whale((long) 1000, "Orca", 20000, Gender.MALE);
    }

    public void testGetEstimated_weight() {
        assertEquals(20000, whale.getEstimated_weight());
    }

    public void testGetGender() {
        assertEquals(Gender.MALE, whale.getGender());
    }

    public void testGetId() {
        assertEquals((long)1000, (long)whale.getId());
    }

    public void testGetSpecies() {
        assertEquals("Orca", whale.getSpecies());
    }
}