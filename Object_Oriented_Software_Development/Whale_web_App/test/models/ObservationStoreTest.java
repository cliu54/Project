package models;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ObservationStoreTest extends TestCase {
    ObservationStore observations;
    LocalDate date;

    public void setUp() throws Exception {
        observations = new ObservationStore();
    }

    public void testAdd() {
        String dateS = "20201201";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
        date = LocalDate.parse(dateS, formatter);
        Observation observation = new Observation(1, Gender.MALE, 20000, "Orca", date);
        assertEquals(0, observations.size());
        observations.add(observation);
        assertEquals(1, observations.size());
    }
}