package com.example.demo.Response;


import com.example.demo.Entity.PassengerActivity;

import java.util.Set;

public class PassengerActivitiesDetails {
    int passengerNumber;
    String name;
    int totalBalance;
    Set<PassengerActivity> passengerActivities;

    public PassengerActivitiesDetails(int passengerNumber, String name, int totalBalance, Set<PassengerActivity> passengerActivities) {
        this.passengerNumber = passengerNumber;
        this.name = name;
        this.totalBalance = totalBalance;
        this.passengerActivities = passengerActivities;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Set<PassengerActivity> getPassengerActivities() {
        return passengerActivities;
    }

    public void setPassengerActivities(Set<PassengerActivity> passengerActivities) {
        this.passengerActivities = passengerActivities;
    }
}
