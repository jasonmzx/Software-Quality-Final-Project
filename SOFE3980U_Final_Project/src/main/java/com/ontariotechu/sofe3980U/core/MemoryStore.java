package com.ontariotechu.sofe3980U.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MemoryStore {

    private static MemoryStore instance = null; // SINGLETON INSTANCE

    //In-Memory app state

    private List<Airport> airportsList;
    private List<Flight> flightNetworkList;

    private MemoryStore() { //Private Constructor, as we'll only be building this object internally
        airportsList = new ArrayList<>();
        flightNetworkList = new ArrayList<>();

        // NA Airports:
        airportsList.add(new Airport("JFK - John F. Kennedy International Airport", "America/New_York", 1));
        airportsList.add(new Airport("LAX - Los Angeles International Airport", "America/Los_Angeles", 2));
        airportsList.add(new Airport("ORD - Chicago O'Hare International Airport", "America/Chicago", 3));
        airportsList.add(new Airport("YYZ - Toronto Pearson International Airport", "America/Toronto", 5));
        airportsList.add(new Airport("YTZ - Billy Bishop Toronto City Airport", "America/Toronto", 8));
        airportsList.add(new Airport("YVR - Vancouver International Airport", "America/Vancouver", 6));   

        // Asia Airports:

        airportsList.add(new Airport("NRT - Narita International Airport", "Asia/Tokyo", 99));
        airportsList.add(new Airport("HKG - Hong Kong International Airport", "Asia/Hong_Kong", 9));
        airportsList.add(new Airport("DXB - Dubai International Airport", "Asia/Dubai", 10));
        airportsList.add(new Airport("BOM - Chatrapati Shivaji International Airport", "Asia/Kolkata", 15)); //mumbai
        airportsList.add(new Airport("PEK - Beijing Capital International Airport", "Asia/Shanghai", 18)); //beijing

        //EU Airports:

        airportsList.add(new Airport("HAM - Hamburg Airport", "Europe/Berlin", 21)); //hamburg | germany
        airportsList.add(new Airport("FRA - Frankfurt Airport", "Europe/Berlin", 11));
        airportsList.add(new Airport("MUC - Munich Airport", "Europe/Berlin", 22));
        airportsList.add(new Airport("CDG - Charles de Gaulle Airport", "Europe/Paris", 12)); //paris | france
        airportsList.add(new Airport("PRG - Vaclav Havel Airport Prague", "Europe/Prague", 13)); //prague | czech
        airportsList.add(new Airport("BRQ - Brno-Turany Airport", "Europe/Prague", 24)); //brno | czech
        airportsList.add(new Airport("BTS - Bratislava Airport", "Europe/Bratislava", 20)); //bratislava | slovakia
        airportsList.add(new Airport("ZAG - Zagreb Airport", "Europe/Zagreb", 23)); //zagreb | croatia
        airportsList.add(new Airport("VIE - Vienna International Airport", "Europe/Vienna", 17)); // vienna | austria
        airportsList.add(new Airport("ZRH - Zurich Airport", "Europe/Zurich", 14));
        airportsList.add(new Airport("LHR - Heathrow Airport", "Europe/London", 4));
        airportsList.add(new Airport("EDI - Edinburgh Airport", "Europe/London", 19));

        // Other:

        airportsList.add(new Airport("MRU - Sr. Seewoosagur Ramgoolam International Airport", "Indian/Mauritius", 16));
        airportsList.add(new Airport("SYD - Sydney Airport", "Australia/Sydney", 7));
        

        // Build some Flight paths (Flight Network)

        LocalTime time1530 = LocalTime.of(15, 30);
        LocalTime time1730 = LocalTime.of(17, 30);

        LocalTime time0615 = LocalTime.of(6, 15);
        LocalTime time0930 = LocalTime.of(9, 30);


        DowDate departDate = new DowDate(0,time1530);
        DowDate arrivalDate= new DowDate(0, time1730);

        flightNetworkList.add(new Flight(airportsList.get(0), airportsList.get(1), departDate,arrivalDate));

        DowDate departDate2 = new DowDate(0,time0615);
        DowDate arrivalDate2= new DowDate(0, time0930);



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
