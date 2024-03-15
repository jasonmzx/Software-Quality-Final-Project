package com.ontariotechu.sofe3980U.core;

public class Flight {
    Airport start, destination;
    DowDate departDate, arrivalDate;

    //Constructor
    public Flight(Airport start, Airport destination, 
                  DowDate departDate, DowDate arrivalDate) {
        this.start = start;
        this.destination = destination;
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
    }
}
