package com.ontariotechu.sofe3980U.booking;

import com.fasterxml.jackson.annotation.JsonProperty; 
import java.util.List;
import java.util.ArrayList;

public class BookingSessionWrap {
    @JsonProperty("uuid") // Specify JSON property name as "uuid"
    private String UUID = "";
    
    @JsonProperty("bookings")
    private List<Booking> bookings = new ArrayList<>();

    // Default constructor needed for JSON deserialization
    public BookingSessionWrap() {
    }

    //Getters and Setters

    public String getUUID() {
        return UUID;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
