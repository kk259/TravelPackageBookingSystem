package com.example.demo.Request;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Destination;
import com.example.demo.Entity.Passenger;

import java.util.HashSet;
import java.util.Set;

public class TravelPackageCreationRequest {
    String travelPackageName;
    int passengerCapacity;
    Set<Destination> destinations = new HashSet<>();
    Set<Activity> activities = new HashSet<>();
    Set<Passenger> passengers = new HashSet<>();

    public TravelPackageCreationRequest(String travelPackageName, int passengerCapacity, Set<Destination> destinations, Set<Activity> activities, Set<Passenger> passengers) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
        this.destinations = destinations;
        this.activities = activities;
        this.passengers = passengers;
    }

    public String getTravelPackageName() {
        return travelPackageName;
    }

    public void setTravelPackageName(String travelPackageName) {
        this.travelPackageName = travelPackageName;
    }

    public int getPassengerCapacity() {
        if(this.passengerCapacity==0){
            return 100;
        }
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Set<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<Destination> destinations) {
        this.destinations = destinations;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}
