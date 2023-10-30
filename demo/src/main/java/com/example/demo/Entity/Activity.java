package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table
@Entity(name = "Activity")
public class Activity {
    @Id
    String name;
    String description;
    int cost;
    int capacity;
    @JsonIgnore
    int occupied;
    @ManyToOne
    @JoinColumn(name = "destination_name")
    private Destination destination;
    @Transient
    int spacesAvailable;

    public Activity(String name, String description, int cost, int capacity, int occupied, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = 100;
        this.capacity = 15;
        this.occupied = 0;
        this.destination = destination;
    }

    public Activity(String name, String description, Destination destination) {
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.cost = 100;
        this.capacity = 15;
        this.occupied = 0;
    }

    public Activity(String name, String description, Destination destination, int capacity) {
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.capacity = capacity;
        this.occupied = 0;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Activity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getSpacesAvailable() {
        return this.capacity - this.occupied;
    }

    public void setSpacesAvailable(int spacesAvailable) {
        this.spacesAvailable = spacesAvailable;
    }
}
