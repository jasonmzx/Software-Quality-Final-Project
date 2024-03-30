package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.UUID;

public class PathFinder {
    
    // Private constructor to prevent instantiation
    private PathFinder() {}

    public static List<Booking> buildBookings(FlightSearchDTO searchDTO) {
        List<Airport> aL = MemoryStore.getInstance().getAirportList();
        Airport start = aL.get(searchDTO.getDepartureAirport());
        Airport end = aL.get(searchDTO.getArrivalAirport());
        int dow = searchDTO.getDepartureDateParsed().getDayOfWeek().getValue();
        LocalTime time0100 = LocalTime.of(1, 0); // 1:00 AM
        DowDate earliestTimeOfDay = new DowDate(dow, time0100);

        List<List<Flight>> flightPathsDeparture = pathFind(start, end, earliestTimeOfDay);
        List<Booking> bookings = new ArrayList<>();

        if (searchDTO.getRoundTrip()) {
            List<List<Flight>> flightPathsReturning = pathFind(end, start, earliestTimeOfDay);

            int minSize = Math.min(flightPathsDeparture.size(), flightPathsReturning.size());


            for (int i = 0; i < minSize; i++) {
                //Change into an ArrayList type to create booking
                ArrayList<Flight> departureFlights = new ArrayList<>(flightPathsDeparture.get(i));
                ArrayList<Flight> returningFlights = new ArrayList<>(flightPathsReturning.get(i));

                //Create new booking
                Booking booking = new Booking(departureFlights, returningFlights, UUID.randomUUID().toString());
                bookings.add(booking);
            }
        } else {
            for (List<Flight> flightPath : flightPathsDeparture) {
                //Change into an ArrayList type to create booking
                ArrayList<Flight> departureFlights = new ArrayList<>(flightPath);

                //Create new booking
                Booking booking = new Booking(departureFlights, UUID.randomUUID().toString()); //Assuming no returning flights for one-way
                bookings.add(booking);
            }
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
        
        //get flights starting from currentAirport
        List<Flight> flightsList = flightsMap.get(currentAirport.getID());

        for (Flight flight : flightsList) {
            //if flight departure <= afterTime, remove flight from list
            if(flight.getDepartDate().compareTo(afterTime) <= 0) {
                flightsList.remove(flight);
            }
        }

        return flightsList;
    }
}
