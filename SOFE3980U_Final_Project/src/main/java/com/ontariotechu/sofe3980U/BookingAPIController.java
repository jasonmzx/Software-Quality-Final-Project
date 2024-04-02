package com.ontariotechu.sofe3980U;

//Java STD Imports:

import java.util.List;
import java.util.UUID;

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
import com.ontariotechu.sofe3980U.core.restmodels.BookingSubDTO;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import com.ontariotechu.sofe3980U.booking.Booking;
import com.ontariotechu.sofe3980U.booking.BookingSessionWrap;
import com.ontariotechu.sofe3980U.core.MemoryStore;
import com.ontariotechu.sofe3980U.core.PathFinder;

@RestController
public class BookingAPIController {

    // * All DTO `Data Transfer Objects` are like an Interface for Spring, where the
    // @RequestBody handles serializairton of the class to JSON

    @PostMapping("/search_flights")
    public ResponseEntity<String> searchFlights(@RequestBody FlightSearchDTO searchParameters) {

        if (searchParameters.getDtoUuid() != null) {
            System.out.println("UUID: " + searchParameters.getDtoUuid());
        } else {
            System.out.println("UUID: N/A");
        }

        // ! DEBUG --- Format the parameters for display or further processing
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
        // ! DEBUG --- Format the parameters for display or further processing ^^^

        // * Assertions */
        try {

            // Validation Passed, parse the dates
            searchParameters.parseDep(searchParameters.getDepartureDate());

            if (searchParameters.getRoundTrip()) {
                searchParameters.parseRet(searchParameters.getReturnDate());
            }

            Utility.validateRequest(searchParameters);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Return a 400 status
        }

        List<Booking> bookingListA = PathFinder.buildBookings(searchParameters);

        // Monkey Patching for adding pretty dates on bookings
        for (Booking booking : bookingListA) {
            
            booking.setVerboseOutDateStr(searchParameters.getDepartureDateParsed());

            if (searchParameters.getRoundTrip()) {
                booking.setVerboseInDateStr(searchParameters.getReturnDateParsed());
            }

        }

        // >>>>>>>> Session Wrapping for JSON and UUID

        MemoryStore memoryStore = MemoryStore.getInstance(); // get singleton inst

        String sessionID = UUID.randomUUID().toString();

        if (searchParameters.getDtoUuid() != null) {
            sessionID = searchParameters.getDtoUuid(); // if UUID is provided, use it
        }

        memoryStore.setBookingBrowseList(sessionID, bookingListA);

        BookingSessionWrap sessResponseObj = new BookingSessionWrap();
        sessResponseObj.setUUID(sessionID);
        sessResponseObj.setBookings(bookingListA);

        String jsonString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(sessResponseObj);
            System.out.println(jsonString);
            // Now jsonString contains the JSON representation of test_booking
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error appropriately
        }

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping("/submit_booking")
    public ResponseEntity<String> submitBooking(@RequestBody BookingSubDTO bookingDTO) {
        try {
            System.out.println("Validating booking...");
            System.out.println("User UUID: " + bookingDTO.getUserUUID());
            System.out.println("User Name: " + bookingDTO.getUserName());
            System.out.println("Booking UUID: " + bookingDTO.getBookingUUID());

            MemoryStore memoryStore = MemoryStore.getInstance(); // get singleton inst
            List<Booking> BBL = memoryStore.getBookingBrowseList(bookingDTO.getUserUUID());

            Boolean found = false;

            for (Booking booking : BBL) {
                System.out.println("Booking UUID: [LOOP]" + booking.getUUID());

                if (booking.getUUID().equals(bookingDTO.getBookingUUID())) {

                    booking.setNameOfPassenger(bookingDTO.getUserName()); // Set passenger name

                    memoryStore.addBookingToBookedList(bookingDTO.getUserUUID(), booking);
                    System.out.println("Booking submitted successfully!");

                    return new ResponseEntity<>("Booking submitted successfully!", HttpStatus.OK);
                }

            }

            if (found == false) {
                return new ResponseEntity<>("Booking not found!", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Booking submitted successfully!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my_bookings")
    public ResponseEntity<String> getMyBookings(@RequestParam String userUUID) {
        MemoryStore memoryStore = MemoryStore.getInstance(); // get singleton inst
        List<Booking> BBL = memoryStore.getBookedListByUUID(userUUID);

        System.out.println("User UUID: " + userUUID);

        String jsonString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(BBL);
            System.out.println(jsonString);
            // Now jsonString contains the JSON representation of test_booking
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error appropriately
        }

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

}
