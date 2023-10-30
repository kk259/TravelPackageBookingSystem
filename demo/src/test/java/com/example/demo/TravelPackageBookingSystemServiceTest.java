package com.example.demo;

import com.example.demo.Entity.*;
import com.example.demo.Request.TravelPackageCreationRequest;
import com.example.demo.Response.PassengerActivitiesDetails;
import com.example.demo.Response.TravelPackageItinerary;
import com.example.demo.Response.TravelPackagePassengerList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TravelPackageBookingSystemServiceTest {
    @Autowired
    public TravelPackageBookingSystemService travelPackageBookingSystemService;
    String travelPackageName;
    int passengerCapacity;
    Set<Destination> destinations;
    Set<Activity> activities;
    Set<Passenger> passengers;
    @BeforeEach
    void setUp(){
        travelPackageName = "Romantic Escape";
        passengerCapacity = 50;
        destinations = new HashSet<>();
        Destination Paris = new Destination("Paris, France");
        Destination Venice = new Destination("Venice, Italy");
        destinations.add(Paris);
        destinations.add(Venice);
        activities = new HashSet<>();
        activities.add(new Activity
                ("Dinner",
                        "Eiffel Tower Dinner in Paris",
                        Paris,
                        1
                ));
        activities.add(new Activity
                ("Museum Tour",
                        "Louvre Museum Tour in Paris",
                        Paris
                ));

        passengers = new HashSet<>();
        passengers.add(new Passenger(
                10,
                "ram",
                Passenger.PassengerType.STANDARD
        ));
        passengers.add(new Passenger(
                11,
                "john",
                Passenger.PassengerType.GOLD
        ));
        passengers.add(new Passenger(
                12,
                "shaam",
                Passenger.PassengerType.PREMIUM
        ));
        TravelPackageCreationRequest travelPackageCreationRequest = new TravelPackageCreationRequest(
                travelPackageName,
                passengerCapacity,
                destinations,
                activities,
                passengers
        );
        travelPackageBookingSystemService.createTravelPackage(travelPackageCreationRequest);
    }
    // no need to test createPackage as we are able to get the package data properly by get methods
    @Test
    public void testGetTravelPackageItinerary() {
        TravelPackageItinerary travelPackageItinerary = travelPackageBookingSystemService.getTravelPackageItinerary(travelPackageName);
        Set<String> itineraryDestinationNames = travelPackageItinerary.getDestinations().stream().map(
                destinationWithActivites -> (destinationWithActivites.getDestinationName())).collect(Collectors.toSet());
        Set<String> expectedDestinationNames = destinations.stream().map(
                destination -> (destination.getDestinationName())).collect(Collectors.toSet());
        Assertions.assertIterableEquals(expectedDestinationNames,itineraryDestinationNames);
    }
    @Test
    public void testGetTravelPackagePassengerList(){
        TravelPackagePassengerList travelPackagePassengerList = travelPackageBookingSystemService.getTravelPackagePassengerList(travelPackageName);
        Set<String> expectedPassengersName = passengers.stream().map(
                passenger -> passenger.getName()).collect(Collectors.toSet());
        Set<String> itineraryPassengersNames = travelPackagePassengerList.getPassengers().stream().map(
                passenger-> passenger.getPassengerName()).collect(Collectors.toSet());
        Assertions.assertIterableEquals(expectedPassengersName,itineraryPassengersNames);
    }
    @Test
    public void testSignUpForActivity() {
        travelPackageBookingSystemService.signUpForActivity(10,"Dinner");
        PassengerActivitiesDetails passengerActivitiesDetails = travelPackageBookingSystemService.getPassengerActivitiesDetails(10);
        boolean activityFound = passengerActivitiesDetails.getPassengerActivities().stream().anyMatch(activity -> activity.getActivityName().equals("Dinner"));
        Assertions.assertTrue(activityFound);
    }
    @Test
    public void testGetActivitiesWithAvailableSpaces(){
        Set<Activity> activitiesBeforeSigningUp = travelPackageBookingSystemService.getActivitiesWithAvailableSpaces(
                travelPackageName);
        boolean dinnerActivityUnoccupied = activitiesBeforeSigningUp.stream().anyMatch(activity -> activity.getName().equals("Dinner"));
        Assertions.assertTrue(dinnerActivityUnoccupied);
        travelPackageBookingSystemService.signUpForActivity(10,"Dinner");
        Set<Activity> activitiesAfterSigningUp = travelPackageBookingSystemService.getActivitiesWithAvailableSpaces(
                travelPackageName);
        dinnerActivityUnoccupied = activitiesAfterSigningUp.stream().anyMatch(activity -> activity.getName().equals("Dinner"));
        Assertions.assertFalse(dinnerActivityUnoccupied);
    }
    @Test
    public void testGetPassengerActivitiesDetails()  {
        travelPackageBookingSystemService.signUpForActivity(10,"Museum Tour"); // 10 is Standard Passenger
        Set<PassengerActivity> activities = travelPackageBookingSystemService.getPassengerActivitiesDetails(10).getPassengerActivities();
        Set<String> activityNames = activities.stream().map(activity-> activity.getActivityName()).collect(Collectors.toSet());
        Set<Integer> amountPaid = activities.stream().map(activity-> activity.getAmountPaid()).collect(Collectors.toSet());
        boolean activityFound = activityNames.stream().anyMatch(activity -> activity.equals("Museum Tour"));
        // passenger number 10 is standard passenger so he will pay activity cost Rs 100 without any discount
        boolean amountPaidFound = amountPaid.stream().anyMatch(amount -> amount.equals(100));
        assertTrue(activityFound);
        assertTrue(amountPaidFound);

        travelPackageBookingSystemService.signUpForActivity(11,"Museum Tour"); // 11 is Gold Passenger
        activities = travelPackageBookingSystemService.getPassengerActivitiesDetails(11).getPassengerActivities();
        activityNames = activities.stream().map(activity-> activity.getActivityName()).collect(Collectors.toSet());
        amountPaid = activities.stream().map(activity-> activity.getAmountPaid()).collect(Collectors.toSet());
        activityFound = activityNames.stream().anyMatch(activity -> activity.equals("Museum Tour"));
        // passenger number 11 is gold passenger so he will get 10% on activity cost Rs 100 which is 90
        amountPaidFound = amountPaid.stream().anyMatch(amount -> amount.equals(90));
        assertTrue(activityFound);
        assertTrue(amountPaidFound);

        travelPackageBookingSystemService.signUpForActivity(12,"Museum Tour"); // 12 is Premium Passenger
        activities = travelPackageBookingSystemService.getPassengerActivitiesDetails(12).getPassengerActivities();
        activityNames = activities.stream().map(activity-> activity.getActivityName()).collect(Collectors.toSet());
        amountPaid = activities.stream().map(activity-> activity.getAmountPaid()).collect(Collectors.toSet());
        activityFound = activityNames.stream().anyMatch(activity -> activity.equals("Museum Tour"));
        // passenger number 12 is premium passenger so he will get free activity
        amountPaidFound = amountPaid.stream().anyMatch(amount -> amount.equals(0));
        assertTrue(activityFound);
        assertTrue(amountPaidFound);
    }
}
