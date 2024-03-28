package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import java.util.List;

public class PathFinder {
    
    // Private constructor to prevent instantiation
    private PathFinder() {}


    public static List<Booking> buildBookings(FlightSearchDTO searchDTO) {
        // Implementation goes here
        System.out.println("PathFinder.buildBookings() called.");
        throw new UnsupportedOperationException("Path finding not implemented yet.");

    }

    // Placeholder for the pathFind function
    public static List<Flight> pathFind(MemoryStore store, Airport airA, Airport airB, DowDate departAfter, DowDate arriveBy) {
        // Implementation goes here
        throw new UnsupportedOperationException("Path finding not implemented yet.");
    }
}
