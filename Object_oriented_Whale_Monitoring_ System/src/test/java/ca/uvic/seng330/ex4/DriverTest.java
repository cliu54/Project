/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.uvic.seng330.ex4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


class AppTest {

    WhaleMonitoringSystem testSystem;

    @BeforeEach public void setup() {
        testSystem = new WhaleMonitoringSystem();

        Location locA = new Location(53.609892, -155.097642);
        Location locB = new Location(17.588179, -117.890618);
        Location locC = new Location(47.398349, -128.789045);
        Location locD = new Location(33.333594, -135.937486);
        Location locE = new Location(49.837982, -139.160138);
        Location locF = new Location(55.445636, -144.023423);

        Date timeA = new Date(2020, Calendar.JANUARY, 1);
        Date timeB = new Date(2020, Calendar.JANUARY, 12);
        Date timeC = new Date(2020, Calendar.FEBRUARY, 3);
        Date timeD = new Date(2020, Calendar.MARCH, 1);
        Date timeE = new Date(2020, Calendar.MARCH, 7);

        testSystem.addReporter("Alice");
        testSystem.addWhale(Whale.Gender.MALE, Whale.Breed.BLUE, true, 200, 10);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(0), testSystem.getWhaleCatalog().getWhale(0), locA, timeA);

        testSystem.addReporter("Bob");
        testSystem.addWhale(Whale.Gender.MALE, Whale.Breed.KILLER, true, 250, 12);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(1), testSystem.getWhaleCatalog().getWhale(1), locB, timeB);
        testSystem.addWhale(Whale.Gender.MALE, Whale.Breed.BELUGA, true, 230, 12);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(1), testSystem.getWhaleCatalog().getWhale(2), locC, timeB);

        testSystem.addReporter("Rose");
        testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.KILLER, true, 200, 11);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(2), testSystem.getWhaleCatalog().getWhale(3), locD, timeC);

        testSystem.addReporter("John");
        testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.BELUGA, true, 210, 17);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(3), testSystem.getWhaleCatalog().getWhale(4), locE, timeD);


        testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.BLUE, true, 300, 25);
        testSystem.addObservation(testSystem.getReporterCatalog().getReporter(0), testSystem.getWhaleCatalog().getWhale(5), locF, timeE);
    }

    @Test public void testCompareWhaleByLength() {
        WhaleCatalog catalog = testSystem.getWhaleCatalog();
        catalog.sortByLength();

        for (int i = 0; i < 5; i++)
        {
            assertTrue(catalog.getWhale(i).getLength() <= catalog.getWhale(i + 1).getLength());
        }
    }

    @Test public void testCompareWhaleByWeight() {
        WhaleCatalog catalog = testSystem.getWhaleCatalog();
        catalog.sortByWeight();

        for (int i = 0; i < 5; i++)
        {
            assertTrue(catalog.getWhale(i).getWeight() <= catalog.getWhale(i + 1).getWeight());
        }
    }

    @Test public void testSortObservationByID() {
        ObservationCatalog catalog = testSystem.getObservationCatalog();
        catalog.sortById();

        for (int i = 0; i < 5; i++)
        {
            assertTrue(catalog.getObservation(i).getObservationId() <= catalog.getObservation(i + 1).getObservationId());
        }
    }

    @Test public void testSearchObservationByID() {
        ObservationCatalog catalog = testSystem.getObservationCatalog();

        assertEquals(catalog.searchById(0).get(), catalog.getObservation(0));
        assertEquals(catalog.searchById(1).get(), catalog.getObservation(1));
        assertEquals(catalog.searchById(2).get(), catalog.getObservation(2));
        assertEquals(catalog.searchById(3).get(), catalog.getObservation(3));
        assertEquals(catalog.searchById(4).get(), catalog.getObservation(4));
        assertEquals(catalog.searchById(5).get(), catalog.getObservation(5));
        assertTrue(catalog.searchById(999).isEmpty());

        ObservationCatalog empty = new ObservationCatalog();
        assertTrue(empty.searchById(0).isEmpty());
    }


    @Test public void testSortObservationByLocation() {
        ObservationCatalog catalog = testSystem.getObservationCatalog();
        catalog.sortByLocation();

        for (int i = 0; i < 5; i++)
        {
            assertTrue(catalog.getObservation(i).getLocation().longitude <= catalog.getObservation(i + 1).getLocation().longitude);
        }
    }


    @Test public void testSearchByLocation(){
        ObservationCatalog catalog = testSystem.getObservationCatalog();
        WhaleCatalog whales = testSystem.getWhaleCatalog();

        assertEquals(catalog.searchByLocation(53.609892, -155.097642).get().getWhale(), whales.getWhale(0));
        assertEquals(catalog.searchByLocation(17.588179, -117.890618).get().getWhale(), whales.getWhale(1));
        assertEquals(catalog.searchByLocation(47.398349, -128.789045).get().getWhale(), whales.getWhale(2));
        assertEquals(catalog.searchByLocation(33.333594, -135.937486).get().getWhale(), whales.getWhale(3));
        assertEquals(catalog.searchByLocation(49.837982, -139.160138).get().getWhale(), whales.getWhale(4));
        assertEquals(catalog.searchByLocation(55.445636, -144.023423).get().getWhale(), whales.getWhale(5));
        assertTrue(catalog.searchByLocation(17.588179, -139.160138).isEmpty());

        ObservationCatalog empty = new ObservationCatalog();
        assertTrue(empty.searchByLocation(53.609892, -155.097642).isEmpty());
    }

    @Test public void testSortByWeight() {
        WhaleCatalog catalog = testSystem.getWhaleCatalog();
        catalog.sortByWeight();

        assertEquals(catalog.getWhale(0).getWeight(),200);
        assertEquals(catalog.getWhale(1).getWeight(),200);
        assertEquals(catalog.getWhale(2).getWeight(),210);
        assertEquals(catalog.getWhale(3).getWeight(),230);
        assertEquals(catalog.getWhale(4).getWeight(),250);
        assertEquals(catalog.getWhale(5).getWeight(),300);
    }

    @Test public void testSortByLength() {
        WhaleCatalog catalog = testSystem.getWhaleCatalog();
        catalog.sortByLength();

        assertEquals(catalog.getWhale(0).getLength(),10);
        assertEquals(catalog.getWhale(1).getLength(),11);
        assertEquals(catalog.getWhale(2).getLength(),12);
        assertEquals(catalog.getWhale(3).getLength(),12);
        assertEquals(catalog.getWhale(4).getLength(),17);
        assertEquals(catalog.getWhale(5).getLength(),25);

    }


    @Test public void testIllegalWhale() {

        assertThrows(NullPointerException.class, () -> {testSystem.addWhale(null, Whale.Breed.BLUE, true, 300, 25);});
        assertThrows(NullPointerException.class, () -> {testSystem.addWhale(Whale.Gender.FEMALE, null, true, 300, 25);});
        assertThrows(NullPointerException.class, () -> {testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.BLUE, null, 300, 25);});
        assertThrows(NumberFormatException.class, () -> {testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.BLUE, true, -1, 25);});
        assertThrows(NumberFormatException.class, () -> {testSystem.addWhale(Whale.Gender.FEMALE, Whale.Breed.BLUE, true, 300, -1);});
    }

    @Test public void testIllegalObservation(){
        testSystem.addReporter("Alice");
        testSystem.addWhale(Whale.Gender.MALE, Whale.Breed.BLUE, true, 200, 10);
        Location locA = new Location(53.609892,-155.097642);
        Date timeA = new Date(2020, Calendar.JANUARY, 1);

        assertThrows(NullPointerException.class, () -> {testSystem.addObservation(null, testSystem.getWhaleCatalog().getWhale(0), locA, timeA);});
        assertThrows(NullPointerException.class, () -> {testSystem.addObservation(testSystem.getReporterCatalog().getReporter(0), null, locA, timeA);});
        assertThrows(NullPointerException.class, () -> {testSystem.addObservation(testSystem.getReporterCatalog().getReporter(0), testSystem.getWhaleCatalog().getWhale(0), null, timeA);});
        assertThrows(NullPointerException.class, () -> {testSystem.addObservation(testSystem.getReporterCatalog().getReporter(0), testSystem.getWhaleCatalog().getWhale(0), locA, null);});
    }

    @Test public void testIllegalReporter(){
        assertThrows(NullPointerException.class, () -> {testSystem.addReporter(null);});
    }
}

