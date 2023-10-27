package com.example.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name="PassengerActivity")
public class PassengerActivity {
    @Id
    @SequenceGenerator(name = "passengerActivitySequence", sequenceName = "passengerActivitySequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "passengerActivitySequence")
    int passengerActivityId;
    int passengerNumber;
    String activityName;
    int amountPaid;
    String destinationName;
    public PassengerActivity() {
    }

    public PassengerActivity(int passengerNumber, String activityName, int amountPaid, String destinationName) {
        this.passengerNumber = passengerNumber;
        this.activityName = activityName;
        this.amountPaid = amountPaid;
        this.destinationName = destinationName;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }
}
