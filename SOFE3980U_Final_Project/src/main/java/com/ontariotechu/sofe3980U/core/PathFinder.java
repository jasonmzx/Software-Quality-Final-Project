package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import java.util.Map;
import java.util.UUID;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    
    // Private constructor to prevent instantiation
    private PathFinder() {}


public static List<Booking> buildBookings(FlightSearchDTO searchDTO) {
    
    List<Airport> aL = MemoryStore.getInstance().getAirportList();
    
    Airport start = aL.get(searchDTO.getDepartureAirport());
    Airport end = aL.get(searchDTO.getArrivalAirport());
    
    //Get Departure Date DOW

    //*From 1 Monday to 7 Sunday (1: MON, 2: TUE, 3: WED, 4: THU, 5: FRI, 6: SAT, 7: SUN)
    
    int dow = searchDTO.getDepartureDateParsed().getDayOfWeek().getValue(); 
    LocalTime time0100 = LocalTime.of(1, 0); //1:00 AM

    DowDate earliestTimeOfDay =  new DowDate(dow, time0100);

    List<List<Flight>> flightPaths = pathFind(start, end, earliestTimeOfDay);
    List<Booking> bookings = new ArrayList<>();

    for (List<Flight> flightPath : flightPaths) {
        // Convert List<Flight> to ArrayList<Flight>
        ArrayList<Flight> flightPathArrayList = new ArrayList<>(flightPath);
        
        // Assuming Booking constructor takes ArrayList<Flight> and a String for ID
        Booking booking = new Booking(flightPathArrayList, UUID.randomUUID().toString());
        bookings.add(booking);
    }
    return bookings;
}

    // Find flight paths for a booking
    public static List<List<Flight>> pathFind(Airport start, Airport end, DowDate departAfter) {
        
        List<List<Flight>> allPaths = new ArrayList<>();
        List<Flight> currentPath = new ArrayList<>();
        
        Map<Integer, List<Flight>> flightsMap = MemoryStore.getInstance().getSortedFlights();
        findFlightsRecursive(flightsMap, start, end, departAfter, currentPath, allPaths);
        return allPaths;
    }

    // Find flight paths recursively (DFS)
    private static void findFlightsRecursive(Map<Integer, List<Flight>> flightsMap, Airport current, Airport end, DowDate departAfter, List<Flight> currentPath, List<List<Flight>> allPaths) {
        if (current.equals(end)) {
            //reached destination, add a copy of currentPath to allPaths
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        List<Flight> nextFlights = getNextFlights(flightsMap, current, departAfter);

        for (Flight flight : nextFlights) {
            currentPath.add(flight); //add current flight to path
            findFlightsRecursive(flightsMap, flight.getDestination(), end, flight.getArrivalDate(), currentPath, allPaths);
            currentPath.remove(currentPath.size() - 1); //remove last flight from path (backtrack)
        }
    }

    private static List<Flight> getNextFlights(Map<Integer, List<Flight>> flightsMap, Airport currentAirport, DowDate afterTime) {
        List<Flight> filteredFlights = new ArrayList<>();
        List<Flight> flightsList = flightsMap.get(currentAirport.getID());
        if (flightsList != null) {
            for (Flight flight : flightsList) {
                // Only add flights departing after the specified time
                if(flight.getDepartDate().compareTo(afterTime) > 0) {
                    filteredFlights.add(flight);
                }
            }
        }
        return filteredFlights;
    }
    
}
