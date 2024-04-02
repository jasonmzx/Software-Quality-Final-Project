package com.ontariotechu.sofe3980U.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.ontariotechu.sofe3980U.core.DowDate;
import com.ontariotechu.sofe3980U.core.Flight;

public class Booking {
    ArrayList<Flight> inBound = new ArrayList<Flight>(); //second half (optional)
    ArrayList<Flight> outBound = new ArrayList<Flight>(); //first half
    String NameOfPassenger;
    String UUID;

    // For Display purposes on UI
    String verboseInDateStr;
    String verboseOutDateStr;

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

    // Returns number of stops from starting airport to end (exclusive)
    public int getTotalStops() {
        int totalstops = 0;

        for (Flight flight : this.outBound) {
            totalstops += 1;
        }
        for (Flight flight : this.inBound) {
            totalstops += 1;
        }

        return totalstops - 1;
    }

    // Returns DowDate version of arrival time for final destination
    public DowDate getFinalArrivalDate() {
        DowDate output;

        if (this.inBound.isEmpty()) { //one way
            output = outBound.get(outBound.size() - 1).getArrivalDate();
        }
        else { //round trip
            output = inBound.get(outBound.size() - 1).getArrivalDate();
        }

        return output;
    }

    // Returns DowDate version of departure time for first flight
    public DowDate getFirstDepartureDate() {
        return outBound.get(0).getDepartDate();
    }

    // ------------ Date Formatting & Variable JSON handles --------------

    private String formatDateVerbose(LocalDate date) {
        String pattern = "MMMM d'%s', yyyy";
        int day = date.getDayOfMonth();
        String suffix = getDayOfMonthSuffix(day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format(pattern, suffix));
        return date.format(formatter);
    }

    private String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    //getters and setters for verbose date strings

    public String getVerboseInDateStr() {
        return verboseInDateStr;
    }

    public void setVerboseInDateStr(LocalDate dateIn) {
        this.verboseInDateStr = formatDateVerbose(dateIn);
    }

    public String getVerboseOutDateStr() {
        return verboseOutDateStr;
    }

    public void setVerboseOutDateStr(LocalDate dateOut) {
        this.verboseOutDateStr = formatDateVerbose(dateOut);
    }

}
