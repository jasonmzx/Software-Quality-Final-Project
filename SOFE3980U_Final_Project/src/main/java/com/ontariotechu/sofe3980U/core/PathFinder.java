package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import java.util.ArrayList;
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
    public static List<List<Flight>> pathFind(Airport start, Airport end, DowDate departAfter) {
        // Implementation goes here
        //throw new UnsupportedOperationException("Path finding not implemented yet.");

        List<List<Flight>> allPaths = new ArrayList<>();
        List<Flight> currentPath = new ArrayList<>();
        findFlightsRecursive(start, end, departAfter, currentPath, allPaths);
        return allPaths;
    }

    private static void findFlightsRecursive(Airport current, Airport end, DowDate departAfter, List<Flight> currentPath, List<List<Flight>> allPaths) {
        if (current.equals(end)) {
            // Reached destination, add a copy of currentPath to allPaths
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        List<Flight> nextFlights = getNextFlights(current, departAfter);

        for (Flight flight : nextFlights) {
            if (flight.getDepartDate().isAfter(departAfter)) {
                currentPath.add(flight); // Add current flight to path
                findFlightsRecursive(flight.getDestination(), end, flight.getArrivalDate(), currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1); // Remove last flight from path (backtrack)
            }
        }
    }

    private static List<Flight> getNextFlights(Airport current, DowDate afterTime) {
        // Implementation to get next possible flights from `current` airport after `afterTime`
        // This would involve filtering the stored flights in MemoryStore based on these conditions
        
        List<Flight> flightsList = MemoryStore.getInstance().getSortedFlights();

        for (Flight flight : flightsList) {
            if (flight.getDepartDate().isAfter(afterTime)) {
                
            }
        }

        return new ArrayList<>(); // Placeholder for actual implementation
    }
}
