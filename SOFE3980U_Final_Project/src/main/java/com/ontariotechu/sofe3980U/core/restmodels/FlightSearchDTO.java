package com.ontariotechu.sofe3980U.core.restmodels;

import java.time.LocalDate;

import com.ontariotechu.sofe3980U.core.Utility;

public class FlightSearchDTO {

    private String returnDate;
    private String departureDate;
    private Integer departureAirport;
    private Integer arrivalAirport;
    private Boolean roundTrip;

    private LocalDate returnDateParsed;
    private LocalDate departureDateParsed;

    // Getters and Setters
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Integer departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Integer getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Integer arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Boolean getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(Boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    //-- hidden
    public void parseDep(String departureDate) {
        this.departureDateParsed = Utility.parseDate(departureDate, "MM/dd/yyyy");
    }

    public void parseRet(String departureDate) {
        this.returnDateParsed = Utility.parseDate(returnDate, "MM/dd/yyyy");
    }

    public LocalDate getDepartureDateParsed() {
        return departureDateParsed;
    }

    public LocalDate getReturnDateParsed() {
        return returnDateParsed;
    }
    
}
