package com.ontariotechu.sofe3980U.booking;

public class Booking {
    Trip outBound;
    Trip inBound;
    String UUID;

    // Constructor
    public Booking(Trip outBound, Trip inBound, String UUID) {
        this.outBound = outBound;
        this.inBound = inBound;
        this.UUID = UUID;
    }

    // Method to get the UUID
    public String getUUID() {
        return this.UUID;
    }
}
