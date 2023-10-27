package com.example.demo.Response;

import java.util.List;

public class TravelPackagePassengerList {
    String travelPackageName;
    int passengerCapacity;
    int currentEnrollmentCount;
    List<PassengerDetails> passengers;

    public TravelPackagePassengerList(String travelPackageName, int passengerCapacity, int currentEnrollmentCount, List<PassengerDetails> passengers) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
        this.currentEnrollmentCount = currentEnrollmentCount;
        this.passengers = passengers;
    }

    public String getTravelPackageName() {
        return travelPackageName;
    }

    public void setTravelPackageName(String travelPackageName) {
        this.travelPackageName = travelPackageName;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getCurrentEnrollmentCount() {
        return currentEnrollmentCount;
    }

    public void setCurrentEnrollmentCount(int currentEnrollmentCount) {
        this.currentEnrollmentCount = currentEnrollmentCount;
    }

    public List<PassengerDetails> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDetails> passengers) {
        this.passengers = passengers;
    }
}
