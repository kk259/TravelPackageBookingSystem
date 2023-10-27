package com.example.demo.Response;

import com.example.demo.Entity.Activity;

import java.util.Set;

public class DestinationWithActivites {
    String destinationName;
    Set<Activity> activities;

    public DestinationWithActivites(String destinationName, Set<Activity> activities) {
        this.destinationName = destinationName;
        this.activities = activities;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
