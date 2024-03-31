package com.ontariotechu.sofe3980U.core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//local import
import com.ontariotechu.sofe3980U.booking.Booking;

public class MemoryStore {

    private static MemoryStore instance = null; // SINGLETON INSTANCE

    //In-Memory app state

    private List<Airport> airportsList;
    private List<Flight> flightNetworkList;



    // Bookings 

    private HashMap<String, List<Booking>> SESS_UUID_Browsed_bookings = new HashMap<>(); //Holds the bookings that have been browsed

    private HashMap<String, List<Booking>> UUID_Booked_bookings = new HashMap<>();

    public Airport getAirportByID(int id) {
        for (Airport airport : airportsList) {
            if (airport.getID() == id) {
                return airport;
            }
        }
        return null;
    }

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

        airportsList.add(new Airport("NRT - Narita International Airport", "Asia/Tokyo", 99)); //! Mistake caught by testing, fixed from 8
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

        DowDate departDateSunAM = new DowDate(7,time0615);
        DowDate arrivalDate2SunAM = new DowDate(7, time0930);

        DowDate departDateSunPM = new DowDate(7,time1530);
        DowDate arrivalDateSunPM = new DowDate(7, time1730);

        DowDate departDateMonAM =  new DowDate(1, time0615);
        DowDate arrivalDateMonAM = new DowDate(1, time0930);

        DowDate departDateMonPM = new DowDate(1,time1530);
        DowDate arrivalDateMonPM = new DowDate(1, time1730);

        DowDate departDateTueAM =  new DowDate(2, time0615);
        DowDate arrivalDateTueAM = new DowDate(2, time0930);

        DowDate departDateTuePM = new DowDate(2,time1530);
        DowDate arrivalDateTuePM = new DowDate(2, time1730);

        DowDate departDateWedAM =  new DowDate(3, time0615);
        DowDate arrivalDateWedAM = new DowDate(3, time0930);

        DowDate departDateWedPM = new DowDate(3,time1530);
        DowDate arrivalDateWedPM = new DowDate(3, time1730);

        DowDate departDateThuAM =  new DowDate(4, time0615);
        DowDate arrivalDateThuAM = new DowDate(4, time0930);

        DowDate departDateThuPM = new DowDate(4,time1530);
        DowDate arrivalDateThuPM = new DowDate(4, time1730);

        DowDate departDateFriAM =  new DowDate(5, time0615);
        DowDate arrivalDateFriAM = new DowDate(5, time0930);

        DowDate departDateFriPM = new DowDate(5,time1530);
        DowDate arrivalDateFriPM = new DowDate(5, time1730);

        DowDate departDateSatAM =  new DowDate(6, time0615);
        DowDate arrivalDateSatAM = new DowDate(6, time0930);

        DowDate departDateSatPM = new DowDate(6,time1530);
        DowDate arrivalDateSatPM = new DowDate(6, time1730);

        //TOR to NYC
        flightNetworkList.add(new Flight(getAirportByID(5), getAirportByID(1), departDateSunPM,arrivalDateSunPM));

        //NYC to LA
        flightNetworkList.add(new Flight(getAirportByID(1), getAirportByID(2), departDateSunPM,arrivalDate2SunAM));

        //LA to NYC
        flightNetworkList.add(new Flight(getAirportByID(2), getAirportByID(1), departDateTueAM,arrivalDateTueAM));

        //NYC to TOR   
        flightNetworkList.add(new Flight(getAirportByID(1), getAirportByID(5), departDateTuePM,arrivalDateTuePM));

        //TOR to CHI
        flightNetworkList.add(new Flight(getAirportByID(5), getAirportByID(3), departDateMonAM,arrivalDateMonAM));

        //CHI to LA
        flightNetworkList.add(new Flight(getAirportByID(3), getAirportByID(2), departDateMonPM,arrivalDateMonPM));


    }

    public static synchronized MemoryStore getInstance() {
        if (instance == null) {
            instance = new MemoryStore();
        }
        return instance;
    }



    // -------------- Booking State Getters and Setters ---------------------------

    public void addBookingToBookedList(String uuid, Booking booking) {
        // Check if the bookings hashmap already contains the uuid
        if (UUID_Booked_bookings.containsKey(uuid)) {
            // Add the booking to the existing list
            UUID_Booked_bookings.get(uuid).add(booking);
        } else {
            // Create a new list and add the booking to it, then put it in the hashmap
            List<Booking> newList = new ArrayList<>();
            newList.add(booking);
            UUID_Booked_bookings.put(uuid, newList);
        }
    }

    public List<Booking> getBookedListByUUID(String uuid) {
        // Check if the bookings hashmap contains the uuid
        if (UUID_Booked_bookings.containsKey(uuid)) {
            return UUID_Booked_bookings.get(uuid);
        } else {
            // Return an empty list or null, depending on your preference
            return new ArrayList<>();
            // Alternatively, return null if you prefer to indicate no bookings found.
        }
    }

    //* Session stuff

    public void setBookingBrowseList(String sessionID, List<Booking> bookingList) {
        SESS_UUID_Browsed_bookings.put(sessionID, bookingList);
    }

    public List<Booking> getBookingBrowseList(String sessionID) { //* get OR default built into Map !
       return SESS_UUID_Browsed_bookings.getOrDefault(sessionID, new ArrayList<>());
    }

    // -------------- Getters & Setters ---------------------------


    public List<Airport> getAirportList() {
        return airportsList;
    }

    public List<Flight> getFlightsList() {
        return flightNetworkList;
    }

    public Map<Integer, List<Flight>> getSortedFlights() {
        
        Map<Integer, List<Flight>> outputMap = new HashMap<Integer, List<Flight>>();

        //sort flight into hash map based on starting airport
        for (Flight flight : flightNetworkList) {

            if (outputMap.containsKey(flight.getStart().getID())) {
                //add current flight to pre-existing list in hashmap
                outputMap.get(flight.getStart().getID()).add(flight);
            }
            else {
                List<Flight> new_key_list = new ArrayList<>();
                new_key_list.add(flight);
                //add new list in hash map with current flight
                outputMap.put(flight.getStart().getID(), new_key_list);
            }
        }

        //iterate through the map with a for-each loop
        for (Map.Entry<Integer, List<Flight>> flightListEntry : outputMap.entrySet()) {
            
            //get list of flights
            List<Flight> flightList = flightListEntry.getValue();
            
            //sort list of flights
            Collections.sort(flightList, new Comparator<Flight>() {
                @Override
                public int compare(Flight f1, Flight f2) {
                    return f1.getDepartDate().compareTo(f2.getDepartDate());
                }
            });
        }

        return outputMap;
    }

}
