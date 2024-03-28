package com.ontariotechu.sofe3980U;

//Java STD Imports:
import java.time.LocalDate;

//Spring Imports:
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//Local Imports:
import com.ontariotechu.sofe3980U.core.Utility;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

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
                
        LocalDate parsedDepartureDate = Utility.parseDate(searchParameters.getDepartureDate(), "MM/dd/yyyy");
        LocalDate parsedReturnDate = null;

        if (searchParameters.getReturnDate() != null) { // If the return date is not null, it's a round trip!
            parsedReturnDate = Utility.parseDate(searchParameters.getReturnDate(), "MM/dd/yyyy");
        }

        // * Assertions */
        try {
            Utility.validateRequest(parsedDepartureDate, searchParameters.getDepartureAirport(),
                    searchParameters.getArrivalAirport(), searchParameters.getRoundTrip(), parsedReturnDate);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Return a 400 status
        }

        // If validation passes, return a 200 status

        //Get JSON-Ified List of flights

        

        return new ResponseEntity<>("Request is valid", HttpStatus.OK);
    }

    
}
