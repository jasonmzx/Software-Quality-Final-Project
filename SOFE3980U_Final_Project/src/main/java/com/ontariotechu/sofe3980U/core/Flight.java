package com.ontariotechu.sofe3980U.core;

public class Flight {
    private Airport start;
    private Airport destination;
    private DowDate departDate;
    private DowDate arrivalDate;

    // Default constructor for JSON deserialization
    public Flight() {
    }

    // Constructor
    public Flight(Airport start, Airport destination, 
                  DowDate departDate, DowDate arrivalDate) {
        this.start = start;
        this.destination = destination;
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
    }

    // Getters
    public Airport getStart() {
        return start;
    }

    public Airport getDestination() {
        return destination;
    }

    public DowDate getDepartDate() {
        return departDate;
    }

    public DowDate getArrivalDate() {
        return arrivalDate;
    }

    // Setters
    public void setStart(Airport start) {
        this.start = start;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setDepartDate(DowDate departDate) {
        this.departDate = departDate;
    }

    public void setArrivalDate(DowDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
