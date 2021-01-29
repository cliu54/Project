package controllers;

import junit.framework.TestCase;

public class WhaleDataTest extends TestCase{
    WhaleData WhaleData;

    public void setUp() throws Exception {
        WhaleData = new WhaleData("orca",500, "Male");
    }

    public void testgetEst_size() {
        assertEquals(500, WhaleData.getEst_size());
    }

    public void testgetGender() {
        assertEquals("Male", WhaleData.getGender());
    }

    public void testgetSpecies() {
        assertEquals("orca", WhaleData.getSpecies());
    }

    public void testsetEst_size() {
        WhaleData.setEst_size(100);
        assertEquals(100, WhaleData.getEst_size());
    }

    public void testsetGender() {
        WhaleData.setGender("Female");
        assertEquals("Female", WhaleData.getGender());
    }

    public void testsetSpecies() {
        WhaleData.setSpecies("Blue");
        assertEquals("Blue", WhaleData.getSpecies());
    }
}
