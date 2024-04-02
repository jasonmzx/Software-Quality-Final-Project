package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.core.MemoryStore;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

// JUnit Imports

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;

public class MemoryStoreTest {

    //Airport Tests (Data Integrity Tests)

    @Test
    public void testUniqueAirportIDs() {
        List<Airport> airports = MemoryStore.getInstance().getAirportList();
        assertNotNull(airports);
        assertFalse(airports.isEmpty());

        Set<Integer> ids = new HashSet<>();
        for (Airport airport : airports) {
            assertFalse(ids.contains(airport.getID()));
            ids.add(airport.getID());
        }
    }

    //Flight Network Tests (Data Integrity Tests)


    //TODO



    //Flight List Sorting Tests (White Box Tests)

    @Test
    public void testSortingFlights1() {

        List<Airport> airports = MemoryStore.getInstance().getAirportList();
        List<Flight> flights = new ArrayList<>();
        Map<Integer, List<Flight>> test = MemoryStore.getInstance().getSortedFlights();
        assertNotNull(test);
        //TODO: flights.add(new Flight(airports.get(0), airports.get(1), new DowDate()));
        
    }
}
