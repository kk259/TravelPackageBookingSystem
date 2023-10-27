package com.example.demo.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name="TravelPackage")
public class TravelPackage {
    @Id
    @Column(name = "travel_package_name")
    String travelPackageName;
    @Column(name = "passenger_capacity")
    int passengerCapacity;
    @Transient
    int currentEnrollmentCount;
    @ManyToMany
    @JoinTable(
            name = "travel_package_destinations",
            joinColumns = @JoinColumn(name = "travel_package_name"),
            inverseJoinColumns = @JoinColumn(name = "destination_name")
    )
    private Set<Destination> destinations = new HashSet<>();
    @OneToMany
    @JoinTable(
            name = "travel_package_passengers",
            joinColumns = @JoinColumn(name = "travel_package_name"),
            inverseJoinColumns = @JoinColumn(name = "passenger_number")
    )
    private Set<Passenger> passengers = new HashSet<>();

    public TravelPackage() {
    }

    public TravelPackage(String travelPackageName,  Set<Destination> destinations,int passengerCapacity) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
        this.destinations = destinations;
    }

    public TravelPackage(String travelPackageName, int passengerCapacity, Set<Passenger> passengers) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
        this.passengers = passengers;
    }

    public TravelPackage(String travelPackageName, int passengerCapacity, Set<Destination> destinations, Set<Passenger> passengers) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
        this.destinations = destinations;
        this.passengers = passengers;
    }

    public int getCurrentEnrollmentCount() {
        return this.passengers.size();
    }

    public void setCurrentEnrollmentCount(int currentEnrollmentCount) {
        this.currentEnrollmentCount = currentEnrollmentCount;
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

    public Set<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<Destination> destinations) {
        this.destinations = destinations;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}