package controllers;

import junit.framework.TestCase;

public class ObservationDataTest extends TestCase {
    ObservationData observationData;

    public void setUp() throws Exception {
        observationData = new ObservationData("Male", 10000, "Orca", "20201208");
    }

    public void testGetGender() {
        assertEquals("Male", observationData.getGender());
    }

    public void testGetEst_size() {
        assertEquals(10000, observationData.getEst_size());
    }

    public void testGetSpecies() {
        assertEquals("Orca", observationData.getSpecies());
    }

    public void testGetSightingDate() {
        assertEquals("20201208", observationData.getSightingDate());
    }

    public void testSetGender() {
        observationData.setGender("Female");
        assertEquals("Female", observationData.getGender());
    }

    public void testSetEst_size() {
        observationData.setEst_size(1);
        assertEquals(1, observationData.getEst_size());
    }

    public void testSetSpecies() {
        observationData.setSpecies("Beluga");
        assertEquals("Beluga", observationData.getSpecies());
    }

    public void testSetSightingDate() {
        observationData.setSightingDate("20190101");
        assertEquals("20190101", observationData.getSightingDate());
    }
}