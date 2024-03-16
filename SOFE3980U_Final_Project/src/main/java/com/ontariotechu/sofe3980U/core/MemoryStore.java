package com.ontariotechu.sofe3980U.core;

import java.util.ArrayList;
import java.util.List;

public class MemoryStore {

    private static MemoryStore instance = null; // SINGLETON INSTANCE
    private List<Airport> airportsList;

    private MemoryStore() { //Private Constructor, as we'll only be building this object here
        airportsList = new ArrayList<>();

        // Populate the list with some airports
        airportsList.add(new Airport("John F. Kennedy International Airport", "America/New_York", 1));
        airportsList.add(new Airport("Los Angeles International Airport", "America/Los_Angeles", 2));
        airportsList.add(new Airport("Chicago O'Hare International Airport", "America/Chicago", 3));
        airportsList.add(new Airport("Heathrow Airport", "Europe/London", 4));
    }

    public static synchronized MemoryStore getInstance() {
        if (instance == null) {
            instance = new MemoryStore();
        }
        return instance;
    }

    // -------------- Getters & Setters ---------------------------

        public List<Airport> getAirportList() {
        return airportsList;
    }

    public List<Flight> getFlightsList() {
        return null;
    }

}
