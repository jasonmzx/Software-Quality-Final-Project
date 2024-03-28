package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.core.MemoryStore;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

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

}
