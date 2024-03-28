package com.ontariotechu.sofe3980U.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

public abstract class Utility {
    public static LocalDate parseDate(String dateString, String format) {
        // Create a formatter using the given format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Parse the date string into a LocalDate object
        return LocalDate.parse(dateString, formatter);
    }

    public static int validateRequest(FlightSearchDTO searchParameters) {

    LocalDate departureDate = searchParameters.getDepartureDateParsed();
    int departureAirport = searchParameters.getDepartureAirport();
    int arrivalAirport = searchParameters.getArrivalAirport();
    boolean roundTrip = searchParameters.getRoundTrip();
    LocalDate returnDate = searchParameters.getReturnDateParsed();

        
        // Ensure departure and arrival airports are not the same
        if (departureAirport == arrivalAirport) {
            throw new IllegalArgumentException("Departure airport and arrival airport cannot be the same.");
        }

        // Ensure departure date is earlier than return date
        if (roundTrip && (departureDate == null || returnDate == null || departureDate.isAfter(returnDate))) {
            throw new IllegalArgumentException("Departure date must be earlier than return date for round trips.");
        }

        // If roundTrip is false, return date should be null
        if (!roundTrip && returnDate != null) {
            throw new IllegalArgumentException("Return date must be null for one-way trips.");
        }

        // Add more assertions as needed...

        return 0;
    }

}
