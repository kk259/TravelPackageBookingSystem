package com.example.demo.Response;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Destination;

import java.util.Set;

public class TravelPackageItinerary {
    String travelPackageName;
    private Set<DestinationWithActivites> destinations;

    public TravelPackageItinerary() {
    }

    public TravelPackageItinerary(String travelPackageName, Set<DestinationWithActivites> destinations) {
        this.travelPackageName = travelPackageName;
        this.destinations = destinations;
    }

    public String getTravelPackageName() {
        return travelPackageName;
    }

    public void setTravelPackageName(String travelPackageName) {
        this.travelPackageName = travelPackageName;
    }

    public Set<DestinationWithActivites> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<DestinationWithActivites> destinations) {
        this.destinations = destinations;
    }
}
