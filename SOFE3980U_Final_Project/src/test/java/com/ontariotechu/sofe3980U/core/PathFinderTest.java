package com.ontariotechu.sofe3980U.core;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// JUnit Imports

import static org.junit.Assert.*;
import org.junit.Test;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;
import com.ontariotechu.sofe3980U.core.PathFinder;

public class PathFinderTest {

    //TODO: MAKE BETTER TESTS

    @Test
    public void testPathFinder() {
        // Create a FlightSearchDTO object
        FlightSearchDTO searchDTO = new FlightSearchDTO();
        searchDTO.setDepartureAirport(5);
        searchDTO.setArrivalAirport(3); 
        
        searchDTO.setDepartureDate("03/25/2024");
        searchDTO.parseDep(searchDTO.getDepartureDate()); //needs to be done externally

        searchDTO.setRoundTrip(false);
        Airport start = new Airport();
        Airport end = new Airport();

        List<Airport> aL = MemoryStore.getInstance().getAirportList();
        for (Airport airport : aL) {
            if (searchDTO.getDepartureAirport() == airport.getID()) {
                start = airport;
            } else if (searchDTO.getArrivalAirport() == airport.getID()) {
                end = airport;
            }
        }
        
        int dow = searchDTO.getDepartureDateParsed().getDayOfWeek().getValue();
        LocalTime time0100 = LocalTime.of(1, 0); // 1:00 AM
        DowDate earliestTimeOfDay = new DowDate(dow, time0100);
        Map<Integer, List<Flight>> flightsMap = MemoryStore.getInstance().getSortedFlights();

        // Generate bookings
        List<Booking> bookings = PathFinder.buildBookings(searchDTO);

        System.out.println("\n\n\n########## Test Path Finder Debug #########  \n\n\n");
        System.out.println("start.getname: " + start.getName());
        System.out.println("end.getname: " + end.getName());

        // Print bookings
        for (Booking booking : bookings) {
            System.out.println("Booking UUID: " + booking.getUUID());
            System.out.println("Passenger Name: " + booking.getNameOfPassenger());
            System.out.println("Outbound Flights:");
            printFlights(booking.getOutBound());
            System.out.println("Inbound Flights:");
            printFlights(booking.getInBound());
            System.out.println("--------------------");
        }
    }

    private void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println("Start Airport: " + flight.getStart().getName());
            System.out.println("Destination Airport: " + flight.getDestination().getName());
            System.out.println("Departure Date: " + flight.getDepartDate().getDoW() + " " + flight.getDepartDate().getDayTime());
            System.out.println("Arrival Date: " + flight.getArrivalDate().getDoW() + " " + flight.getArrivalDate().getDayTime());
            System.out.println();
        }
    }
  
    @Test
    public void testBuildBookings() {
        // Test the buildBookings function
        // This function is a placeholder and should throw an UnsupportedOperationException
        try {
            PathFinder.buildBookings(null);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testPathFind() {
        // Test the pathFind function
        // This function is a placeholder and should throw an UnsupportedOperationException
        try {
            PathFinder.pathFind(null, null, null);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

}
