package com.example.demo.Entity;

import javax.persistence.*;

@Table
@Entity(name="Passenger")
public class Passenger {
    @Id
    @SequenceGenerator(name = "passengerSequence", sequenceName = "passengerSequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "passengerSequence")
    int passengerNumber;
    String name;
    PassengerType type;
    int totalBalance;
    int spent;
    @Transient
    int remainingBalance;
    public enum PassengerType {
        STANDARD,
        GOLD,
        PREMIUM
    }

    public Passenger(String name, PassengerType type) {
        this.name = name;
        this.type = type;
        this.totalBalance = 1000;
        this.spent = 0;
    }

    public Passenger(String name, PassengerType type, int totalBalance) {
        this.name = name;
        this.type = type;
        this.totalBalance = totalBalance;
        this.spent = 0;
    }
    public Passenger(String name) {
        this.name = name;
        this.type = PassengerType.STANDARD;
        this.totalBalance = 1000;
        this.spent = 0;
    }
    public Passenger() {
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

