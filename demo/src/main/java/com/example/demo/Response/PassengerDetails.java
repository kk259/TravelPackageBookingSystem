package com.example.demo.Response;

public class PassengerDetails {
    int passengerNumber;
    String passengerName;

    public PassengerDetails(int passengerNumber, String passengerName) {
        this.passengerNumber = passengerNumber;
        this.passengerName = passengerName;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
}
