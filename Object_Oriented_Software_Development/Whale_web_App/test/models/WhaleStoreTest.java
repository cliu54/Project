package models;

import junit.framework.TestCase;

public class WhaleStoreTest extends TestCase {
    WhaleStore whales;

    public void setUp() throws Exception {
        whales = new WhaleStore();
    }

    public void testAdd() {
        Whale whale = new Whale((long) 1000, "Orca", 20000, Gender.MALE);
        assertEquals(0, whales.size());
        whales.add(whale);
        assertEquals(1, whales.size());
    }
}