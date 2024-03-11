package com.ontariotechu.sofe3980U.core;

import java.time.ZoneId;

public class Airport {
    String name;
    ZoneId timeZone; // Use ZoneId for timeZone
    int ID;

    // Constructor
    public Airport(String name, String timeZone, int ID) {
        this.name = name;
        this.timeZone = ZoneId.of(timeZone); // Convert string to ZoneId
        this.ID = ID;
    }

    // Functions to Set Variables
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTimeZone(String timeZone) {
        this.timeZone = ZoneId.of(timeZone); // Convert string to ZoneId
    }

    // Functions to Get Variables
    public int getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTimeZone() {
        return this.timeZone.toString(); // Convert ZoneId to String for return
    }
}
