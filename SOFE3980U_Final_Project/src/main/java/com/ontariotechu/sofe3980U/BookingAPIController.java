package com.ontariotechu.sofe3980U;

//Java STD Imports:

import java.time.LocalDate;

//Spring Imports:

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

//Local Imports:

import com.ontariotechu.sofe3980U.core.Utility;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

@RestController
public class BookingAPIController {

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
    public String searchFlights(@RequestBody FlightSearchDTO searchParameters) {
        // Format the parameters for display or further processing
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

        LocalDate parsedDepartureDate = Utility.parseDate(searchParameters.getDepartureDate(), "MM/dd/yyyy");
        LocalDate parsedReturnDate = null;

        if (searchParameters.getReturnDate() != null) {
            parsedReturnDate = Utility.parseDate(searchParameters.getReturnDate(), "MM/dd/yyyy");
        }
        
        // Here, for demonstration, we're just printing the formatted parameters
        System.out.println(formattedParams);

        // TODO: Write some Assertions for this method before preforming a search:

        // Normally, you would process the search and return the results
        // For now, let's just return the formatted parameters as a response
        return formattedParams;
    }
}
