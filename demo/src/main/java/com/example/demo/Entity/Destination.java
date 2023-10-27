package com.example.demo.Entity;

import javax.persistence.*;

@Table
@Entity(name="Destination")
public class Destination {
    @Id
    @Column(name = "destination_name")
    String destinationName;

    public Destination() {
    }

    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
