package com.ontariotechu.sofe3980U.core;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import java.util.Map;
import java.util.UUID;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.time.LocalTime;
import java.util.UUID;

public class PathFinder {
    
    // Private constructor to prevent instantiation
    private PathFinder() {}

    public static List<Booking> buildBookings(FlightSearchDTO searchDTO) {
        List<Airport> aL = MemoryStore.getInstance().getAirportList();
        Airport start = new Airport();
        Airport end = new Airport();
        int dow = searchDTO.getDepartureDateParsed().getDayOfWeek().getValue();
        LocalTime time0100 = LocalTime.of(1, 0); // 1:00 AM
        DowDate earliestTimeOfDay = new DowDate(dow, time0100);

        //Set proper airports
        for (Airport airport : aL) {
            if (searchDTO.getDepartureAirport() == airport.getID()) {
                start = airport;
            } else if (searchDTO.getArrivalAirport() == airport.getID()) {
                end = airport;
            }
        }

        List<List<Flight>> flightPathsDeparture = pathFind(start, end, earliestTimeOfDay);
        List<Booking> bookings = new ArrayList<>();

        if (searchDTO.getRoundTrip()) {
            List<List<Flight>> flightPathsReturning = pathFind(end, start, earliestTimeOfDay);

            int minSize = Math.min(flightPathsDeparture.size(), flightPathsReturning.size());

            for (int i = 0; i < minSize; i++) 
            {
                //Change into an ArrayList type to create booking
                ArrayList<Flight> departureFlights = new ArrayList<>(flightPathsDeparture.get(i));
                ArrayList<Flight> returningFlights = new ArrayList<>(flightPathsReturning.get(i));

                //Create new booking
                Booking booking = new Booking(departureFlights, returningFlights, UUID.randomUUID().toString());
                bookings.add(booking);
            }
        } else {
            for (List<Flight> flightPath : flightPathsDeparture) 
            {
                //Change into an ArrayList type to create booking
                ArrayList<Flight> departureFlights = new ArrayList<>(flightPath);

                //Create new booking
                Booking booking = new Booking(departureFlights, UUID.randomUUID().toString()); //Assuming no returning flights for one-way
                bookings.add(booking);
            }
        }

        sortBookings(bookings);
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

        List<Flight> nextFlights = getNextFlights(flightsMap, currentPath, current, departAfter);

        for (Flight flight : nextFlights) {
            currentPath.add(flight); //add current flight to path
            findFlightsRecursive(flightsMap, flight.getDestination(), end, flight.getArrivalDate(), currentPath, allPaths);
            currentPath.remove(currentPath.size() - 1); //remove last flight from path (backtrack)
        }
    }

    // Find next steps in flight graph
    public static List<Flight> getNextFlights(Map<Integer, List<Flight>> flightsMap, List<Flight> currentPath, Airport currentAirport, DowDate afterTime) {
        List<Flight> filteredFlights = new ArrayList<>();
        List<Flight> flightsList = flightsMap.get(currentAirport.getID());
        List<Airport> visitedAirports = new ArrayList<>();

        if (currentPath.size() != 0) {
            visitedAirports.add(currentPath.get(0).getStart());
            for (Flight flight : currentPath) {
                visitedAirports.add(flight.getDestination());
            }
        }

        if (flightsList != null) {
            for (Flight flight : flightsList) {
                // Only add flights departing after the specified time
                // Only add flights with new airports (get rid of cycling)
                if(flight.getDepartDate().compareTo(afterTime) >= 0 && !visitedAirports.contains(flight.getDestination())) {
                    filteredFlights.add(flight);
                }
            }
        }
        return filteredFlights;
    }

    // Sorts bookings first by total stops, then by final arrival time; sorts the argument list and outputs the sorted list
    private static List<Booking> sortBookings(List<Booking> bookings) {

        Map<Integer, List<Booking>> bookingMap = new HashMap<Integer, List<Booking>>();
        List<Booking> output = new ArrayList<>();
        int totalstops = 0;
        int maxstops = 0;

        //sort bookings into hashtable based on total stops
        for (Booking booking : bookings) {
            totalstops = booking.getTotalStops();
            if (bookingMap.containsKey(totalstops)) {
                bookingMap.get(totalstops).add(booking);
            }
            else {
                bookingMap.put(totalstops, new ArrayList<>());
                bookingMap.get(totalstops).add(booking);
                if (totalstops > maxstops) {maxstops = totalstops;}
            }
        }

        //for each possible key in hashtable
        for (int i = 0; i <= maxstops; i++) {
            if (bookingMap.containsKey(i)) {
                //sort hash table row
                Collections.sort(bookingMap.get(i), new Comparator<Booking>() {
                    @Override
                    public int compare(Booking b1, Booking b2) {
                        return b1.getFinalArrivalDate().compareTo(b2.getFinalArrivalDate());
                    }
                });
                
                //add row to output
                for (Booking booking : bookingMap.get(i)) {
                    output.add(booking);
                }
            }
        }

        bookings = output;
        return output;
    }
    
}
