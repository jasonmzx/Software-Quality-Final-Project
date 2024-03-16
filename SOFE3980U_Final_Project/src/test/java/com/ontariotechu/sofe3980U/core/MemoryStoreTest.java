package com.ontariotechu.sofe3980U.core;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.Repeat;

public class MemoryStoreTest {
    @Repeat(5)
    @Test
    public static void getInstanceTest() {
        MemoryStore store = MemoryStore.getInstance();
        assertNotEquals(store, null);
        assertEquals(MemoryStore.class, store);
    }

    @Repeat(5)
    @Test
    public static void getInstanceMatchTest() {
        assertEquals(MemoryStore.getInstance(), MemoryStore.getInstance());
    }

    @Test
    public static void getFlightsTest() {
        List<Flight> flights = MemoryStore.getInstance().getFlightsList();
        assertNotEquals(null, flights);
        assertFalse(flights.isEmpty());
        assertTrue(flights.getFirst().arrivalDate.DayTime.isBefore(flights.getFirst().arrivalDate.DayTime));
        assertNotEquals(flights.getFirst().destination, null);
        assertNotEquals(flights.getFirst().start, null);
        assertNotEquals(flights.getFirst().start, flights.getFirst().destination);
        for (int i = 1; i < flights.size(); i++) {
            assertNotEquals(flights.get(i-1), flights.get(i));
        }
    }

    @Test
    public static void getAriportTest() {
        List<Airport> airports = MemoryStore.getInstance().getAirportList();
        assertNotNull(airports);
        assertFalse(airports.isEmpty());
        for (int i = 1; i < airports.size(); i++) {
            assertNotEquals(airports.get(i-1), airports.get(i));
        }
    }
}
