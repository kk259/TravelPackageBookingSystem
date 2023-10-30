package com.example.demo;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Passenger;
import com.example.demo.Entity.PassengerActivity;
import com.example.demo.Entity.TravelPackage;
import com.example.demo.Repository.*;
import com.example.demo.Request.TravelPackageCreationRequest;
import com.example.demo.Response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TravelPackageBookingSystemService {
    public final TravelPackageRepository travelPackageRepository;
    public final DestinationRepository destinationRepository;
    private final ActivityRepository activityRepository;
    private final PassengerRepository passengerRepository;
    private final PassengerActivityRepository passengerActivityRepository;
    Logger logger = LoggerFactory.getLogger(TravelPackageBookingSystemService.class);

    @Autowired
    public TravelPackageBookingSystemService(TravelPackageRepository travelPackageRepository, DestinationRepository destinationRepository, ActivityRepository activityRepository, PassengerRepository passengerRepository, PassengerActivityRepository passengerActivityRepository) {
        this.travelPackageRepository = travelPackageRepository;
        this.destinationRepository = destinationRepository;
        this.activityRepository = activityRepository;
        this.passengerRepository = passengerRepository;
        this.passengerActivityRepository = passengerActivityRepository;
    }

    /**
     * Creates a travel package, including its associated destinations and activities in each destination.
     */
    @Transactional
    public boolean createTravelPackage(TravelPackageCreationRequest travelPackageCreationRequest) {
        if (travelPackageCreationRequest.getTravelPackageName().isEmpty()) {
            return false;
        }
        try {
            destinationRepository.saveAll(travelPackageCreationRequest.getDestinations());
            activityRepository.saveAll(travelPackageCreationRequest.getActivities());
            passengerRepository.saveAll(travelPackageCreationRequest.getPassengers());
            TravelPackage travelPackage = new TravelPackage(
                    travelPackageCreationRequest.getTravelPackageName(),
                    travelPackageCreationRequest.getPassengerCapacity(),
                    travelPackageCreationRequest.getDestinations(),
                    travelPackageCreationRequest.getPassengers()
            );
            travelPackageRepository.save(travelPackage);
            return true;
        } catch (DataAccessException ex) {
            logger.error("Database exception occurred: " + ex.getMessage(), ex);
            throw new RuntimeException("Failed to create a travel package due to a database error.", ex);
        } catch (Exception ex) {
            // Handle other exceptions
            logger.error("An unexpected exception occurred: " + ex.getMessage(), ex);
            throw new RuntimeException("Failed to create a travel package due to an unexpected error.", ex);
        }
    }

    /**
     * Calculates the cost of an activity for a passenger based on the type : GOLD, STANDARD AND PREMIUM
     */
    private int calculateAmountForPassenger(Passenger.PassengerType passengerType, int activityCost) {
        if (passengerType == Passenger.PassengerType.STANDARD) {
            return activityCost;
        } else if (passengerType == Passenger.PassengerType.GOLD) {
            return (int) (activityCost * .9);
        } else {
            return 0;
        }
    }

    /**
     * Signs up a passenger for a specified activity, deducting the required amount from the passenger's balance.
     */
    @Transactional
    public Boolean signUpForActivity(int passengerNumber, String activityName) {
        Passenger passenger = passengerRepository.findPassengerByPassengerNumber(passengerNumber);
        Activity activity = activityRepository.findActivityByActivityName(activityName);
        if (passenger == null || activity == null || !isActivityAvailable(activity)) {
            return false;
        }
        int activityCost = activity.getCost();
        int amountForPassenger = calculateAmountForPassenger(passenger.getType(), activityCost);

        if (amountForPassenger > passenger.getRemainingBalance()) {
            return false;
        }
        String destinationName = activity.getDestination().getDestinationName();
        passengerActivityRepository.save(new PassengerActivity(
                passengerNumber,
                activityName,
                amountForPassenger,
                destinationName
        ));
        passenger.setSpent(passenger.getSpent() + amountForPassenger);
        activity.setOccupied(activity.getOccupied() + 1);
        return true;
    }

    private boolean isActivityAvailable(Activity activity) {
        return 0 < activity.getSpacesAvailable();
    }

    /**
     * Print itinerary of the travel package including:
     * travel package name,
     * destinations and details of the activities available at each destination, like name, cost, capacity and description.
     */
    public TravelPackageItinerary getTravelPackageItinerary(String travelPackageName) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByTravelPackageName(travelPackageName);
        Set<DestinationWithActivites> destinationsWithActivites = travelPackage.getDestinations().stream()
                .map(destination -> new DestinationWithActivites(
                        destination.getDestinationName(),
                        activityRepository.findActivitiesByItsDestination(destination)
                ))
                .collect(Collectors.toSet());
        TravelPackageItinerary travelPackageItinerary = new TravelPackageItinerary(travelPackageName,
                destinationsWithActivites);
        return travelPackageItinerary;
    }

    /**
     * Print the passenger list of the travel package including:
     * 1. package name,
     * 2. passenger capacity,
     * 3. number of passengers currently enrolled and
     * 4. name and number of each passenger
     */
    public TravelPackagePassengerList getTravelPackagePassengerList(String travelPackageName) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByTravelPackageName(travelPackageName);
        TravelPackagePassengerList travelPackagePassengerList = new TravelPackagePassengerList(
                travelPackageName,
                travelPackage.getPassengerCapacity(),
                travelPackage.getCurrentEnrollmentCount(),
                travelPackage.getPassengers().stream()
                        .map(passenger -> new PassengerDetails(passenger.getPassengerNumber(), passenger.getName()))
                        .collect(Collectors.toList())
        );
        return travelPackagePassengerList;
    }

    /**
     * Print the details of all the activities that still have spaces available, including how many spaces are available
     */
    public Set<Activity> getActivitiesWithAvailableSpaces(String travelPackageName) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByTravelPackageName(travelPackageName);

        Set<Activity> activities = travelPackage.getDestinations().stream()
                .flatMap(destination -> activityRepository.findActivitiesByItsDestination(destination).stream())
                .filter(this::isActivityAvailable)
                .collect(Collectors.toSet());
        return activities;
    }

    /**
     * 3. Print the details of an individual passenger including their
     * 1. name,
     * 2. passenger number,
     * 3. balance (if applicable),
     * 4. list of each activity they have signed up for, including the destination the at which the activity is taking place and the price the passenger paid for the activity.
     */
    public PassengerActivitiesDetails getPassengerActivitiesDetails(int passengerNumber) {
        Passenger passenger = passengerRepository.findPassengerByPassengerNumber(passengerNumber);
        Set<PassengerActivity> passengerActivities = passengerActivityRepository.findPassengerActivitiesByPassengerNumber(passengerNumber);
        PassengerActivitiesDetails passengerActivitiesDetails = new PassengerActivitiesDetails(
                passengerNumber,
                passenger.getName(),
                passenger.getRemainingBalance(),
                passengerActivities
        );
        return passengerActivitiesDetails;
    }
}
