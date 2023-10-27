package com.example.demo;

import com.example.demo.Entity.Activity;
import com.example.demo.Request.TravelPackageCreationRequest;
import com.example.demo.Response.PassengerActivitiesDetails;
import com.example.demo.Response.TravelPackageItinerary;
import com.example.demo.Response.TravelPackagePassengerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/package")
public class TravelPackageBookingSystemController {
    public final TravelPackageBookingSystemService travelPackageBookingSystemService;
    @Autowired
    public TravelPackageBookingSystemController(TravelPackageBookingSystemService travelPackageBookingSystemService) {
        this.travelPackageBookingSystemService = travelPackageBookingSystemService;
    }
    @PostMapping("/createTravelPackage")
    public String createTravelPackage(@RequestBody TravelPackageCreationRequest travelPackageCreationRequest){
       if(travelPackageBookingSystemService.createTravelPackage(travelPackageCreationRequest)){
           return "Package created";
       }
       return "Package creation failure";
    }
    @PostMapping(value="signUpForActivity")
    public String signUpForActivity(@RequestParam(name="passengerNumber", required = true) int passengerNumber,
                                    @RequestParam(name="activityName", required = true) String activityName){
        if(travelPackageBookingSystemService.signUpForActivity(passengerNumber,activityName)) {
            return "activity signed up";
        }
        return "can't sign up activity";
    }
    @GetMapping("/TravelPackageItinerary")
    public TravelPackageItinerary getTravelPackageItinerary(@RequestParam(name="travelPackageName", required = true) String travelPackageName){
        return travelPackageBookingSystemService.getTravelPackageItinerary(travelPackageName);
    }
    @GetMapping("/TravelPackagePassengerList")
    public TravelPackagePassengerList getTravelPackagePassengerList(@RequestParam(name="travelPackageName", required = true) String travelPackageName){
        return travelPackageBookingSystemService.getTravelPackagePassengerList(travelPackageName);
    }
    @GetMapping(value="ActivitiesWithAvailableSpaces")
    public Set<Activity> getActivitiesWithAvailableSpaces(@RequestParam(name="travelPackageName", required = true) String travelPackageName){
        return travelPackageBookingSystemService.getActivitiesWithAvailableSpaces(travelPackageName);
    }
    @GetMapping(value="getPassengerActivitiesDetails")
    public PassengerActivitiesDetails getPassengerActivitiesDetails(@RequestParam(name="passengerNumber", required = true) int passengerNumber){
        return travelPackageBookingSystemService.getPassengerActivitiesDetails(passengerNumber);
    }

}
