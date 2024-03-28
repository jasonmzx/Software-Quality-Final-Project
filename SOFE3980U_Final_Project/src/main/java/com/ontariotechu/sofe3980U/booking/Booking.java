package com.ontariotechu.sofe3980U.booking;

import java.util.ArrayList;

import com.ontariotechu.sofe3980U.core.Flight;

public class Booking {
    ArrayList<Flight> inBound = new ArrayList<Flight>();
    ArrayList<Flight> outBound = new ArrayList<Flight>();
    String NameOfPassenger;
    String UUID;

    // Constructor
    public Booking(ArrayList<Flight> outBound, ArrayList<Flight> inBound, String UUID) {
        this.outBound = outBound;
        this.inBound = inBound;
        this.UUID = UUID;
    }

    public String getUUID() {
        return this.UUID;
    }
}
