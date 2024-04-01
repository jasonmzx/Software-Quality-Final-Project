package com.ontariotechu.sofe3980U.core.restmodels;

public class BookingSubDTO {
    private String userUUID;
    private String userName;
    private String bookingUUID;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookingUUID() {
        return bookingUUID;
    }

    public void setBookingUUID(String bookingUUID) {
        this.bookingUUID = bookingUUID;
    }
}