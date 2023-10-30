package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "Destination")
public class Destination {
    @Id
    @Column(name = "destination_name")
    String destinationName = "dest1";

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
