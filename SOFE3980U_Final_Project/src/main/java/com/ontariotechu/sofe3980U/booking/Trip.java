package com.ontariotechu.sofe3980U.booking;

import com.ontariotechu.sofe3980U.core.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Trip {
    String fullName;
    LocalDateTime departureDay;
    ArrayList<Flight> journey = new ArrayList<Flight>();

    //Constructor
    public Trip(String fullName, LocalDateTime departureDay, ArrayList<Flight> journey) {
        this.fullName = fullName;
        this.departureDay = departureDay;
        this.journey = journey;
    }

    public ArrayList<Flight> getJourney() {
        return this.journey;
    }
}