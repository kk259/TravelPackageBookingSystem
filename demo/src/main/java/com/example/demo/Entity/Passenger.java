package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table
@Entity(name = "Passenger")
public class Passenger {
    @Id
    int passengerNumber;
    String name;
    PassengerType type;
    int totalBalance;
    @JsonIgnore
    int spent;
    @Transient
    @JsonIgnore
    int remainingBalance;

    public enum PassengerType {
        STANDARD,
        GOLD,
        PREMIUM
    }

    public Passenger(int passengerNumber, String name, PassengerType type) {
        this.passengerNumber = passengerNumber;
        this.name = name;
        this.type = type;
        this.totalBalance = 1000;
        this.spent = 0;
    }

    public Passenger(int passengerNumber, String name) {
        this.passengerNumber = passengerNumber;
        this.name = name;
        this.type = PassengerType.STANDARD;
        this.totalBalance = 1000;
        this.spent = 0;
    }

    public Passenger() {
        this.type = PassengerType.STANDARD;
        this.totalBalance = 1000;
        this.spent = 0;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
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

    public PassengerType getType() {
        return type;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getRemainingBalance() {
        return this.getTotalBalance() - this.spent;
    }

    public void setRemainingBalance(int remainingBalance) {
        this.remainingBalance = this.totalBalance - this.spent;
    }
}

