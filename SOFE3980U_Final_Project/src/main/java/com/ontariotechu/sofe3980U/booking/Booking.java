package com.ontariotechu.sofe3980U.booking;

import java.util.ArrayList;

import com.ontariotechu.sofe3980U.core.Flight;

public class Booking {
    ArrayList<Flight> inBound = new ArrayList<Flight>();
    ArrayList<Flight> outBound = new ArrayList<Flight>();
    String NameOfPassenger;
    String UUID;

    // Default constructor needed for JSON deserialization
    public Booking() {
    }

    // Constructor
    public Booking(ArrayList<Flight> outBound, ArrayList<Flight> inBound, String UUID) {
        this.outBound = outBound;
        this.inBound = inBound;
        this.UUID = UUID;
    }

    // Overload
    public Booking(ArrayList<Flight> outBound, String UUID) {
        this.outBound = outBound;
        this.inBound = new ArrayList<>();
        this.UUID = UUID;
    }

    // Getter and Setter for inBound
    public ArrayList<Flight> getInBound() {
        return inBound;
    }

    public void setInBound(ArrayList<Flight> inBound) {
        this.inBound = inBound;
    }

    // Getter and Setter for outBound
    public ArrayList<Flight> getOutBound() {
        return outBound;
    }

    public void setOutBound(ArrayList<Flight> outBound) {
        this.outBound = outBound;
    }

    // Getter and Setter for NameOfPassenger (assuming you might use it later)
    public String getNameOfPassenger() {
        return NameOfPassenger;
    }

    public void setNameOfPassenger(String nameOfPassenger) {
        NameOfPassenger = nameOfPassenger;
    }

    // Getter for UUID
    public String getUUID() {
        return UUID;
    }

    // Setter for UUID
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
