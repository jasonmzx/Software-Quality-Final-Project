package com.ontariotechu.sofe3980U;

//Java STD Imports:
import java.time.LocalDate;
import java.util.ArrayList;

//Spring Imports:
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//json
import com.fasterxml.jackson.databind.ObjectMapper;

//Local Imports:
import com.ontariotechu.sofe3980U.core.Utility;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.core.Flight;


@RestController
public class BookingAPIController {

    // TODO: Remove dependent Tests for this method
    @GetMapping("/bookadd")
    public String addString(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
            @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

        String s = operand1 + operand2;

        return s;
        // test ex: http://localhost:8080/bookadd?operand1=hello&operand2=world :
        // helloworld
    }

    // * All DTO `Data Transfer Objects` are like an Interface for Spring, where the
    // @RequestBody handles serializairton of the class to JSON

    @PostMapping("/search_flights")
    public ResponseEntity<String> searchFlights(@RequestBody FlightSearchDTO searchParameters) {
        
        
        //! DEBUG --- Format the parameters for display or further processing
        String formattedParams = String.format(
                "Departure Date: %s\n" +
                        "Departure Airport: %d\n" +
                        "Arrival Airport: %d\n" +
                        "Round Trip: %s\n" +
                        "Return Date: %s",
                searchParameters.getDepartureDate(),
                searchParameters.getDepartureAirport(),
                searchParameters.getArrivalAirport(),
                searchParameters.getRoundTrip() ? "Yes" : "No",
                searchParameters.getReturnDate() != null ? searchParameters.getReturnDate() : "N/A");

        System.out.println(formattedParams);
        //! DEBUG --- Format the parameters for display or further processing ^^^

        // * Assertions */
        try {
            Utility.validateRequest(searchParameters);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Return a 400 status
        }

        // If validation passes, return a 200 status

        ArrayList<Flight> flights_in = new ArrayList<>();

        //Get JSON-Ified List of flights
        Booking test_booking = new Booking(flights_in, "uuid");
        
        String jsonString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(test_booking);
            System.out.println(jsonString);
            // Now jsonString contains the JSON representation of test_booking
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error appropriately
        }

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    
}
