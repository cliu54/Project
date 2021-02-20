package models;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ObservationTest extends TestCase {
    LocalDate date;
    Observation observation;

    public void setUp() throws Exception {
        String dateS = "20201201";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
        date = LocalDate.parse(dateS, formatter);
        observation = new Observation(1, Gender.MALE, 20000, "Orca", date);
    }

    public void testGetId() {
        assertEquals(1, observation.getId());
    }

    public void testGetGender() {
        assertEquals(Gender.MALE, observation.getGender());
    }

    public void testGetEst_size() {
        assertEquals(20000, observation.getEst_size());
    }

    public void testGetSpecies() {
        assertEquals("Orca", observation.getSpecies());
    }

    public void testGetSightingDate() {
        assertEquals(date, observation.getSightingDate());
    }
}