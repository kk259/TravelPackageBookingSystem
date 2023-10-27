package com.example.demo;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Destination;
import com.example.demo.Entity.Passenger;
import com.example.demo.Entity.TravelPackage;
import com.example.demo.Repository.ActivityRepository;
import com.example.demo.Repository.DestinationRepository;
import com.example.demo.Repository.PassengerRepository;
import com.example.demo.Repository.TravelPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class TravelPackageBookingSystemConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(TravelPackageRepository travelPackageRepository,
                                        DestinationRepository destinationRepository,
                                        ActivityRepository activityRepository,
                                        PassengerRepository passengerRepository
                                        ) {
        return args -> {
            Set<Destination> destinations = new HashSet<>();
            Destination Bali = new Destination("Bali, Indonesia");

            Destination newYork = new Destination("New York City, USA");
            destinations.add(Bali);
            destinations.add(newYork);
            destinationRepository.saveAll(destinations);
            Set<Activity> activities = new HashSet<>();
            activities.add(new Activity
                    ("Hiking",
                            "Explore scenic trails and enjoy the great outdoors",
                            Bali
                    ));
            activities.add(new Activity
                    ("Beach activities",
                            "Relax on the beach, swim, surf, or play beach volleyball.",
                            Bali
                    ));
            activityRepository.saveAll(activities);

            Set<Passenger> passengers = new HashSet<>();
            passengers.add(new Passenger(
                    "raj",
                    Passenger.PassengerType.GOLD
            ));
            passengers.add(new Passenger(
                    "dev"
            ));
            passengers.add(new Passenger(
                    "raha",
                    Passenger.PassengerType.PREMIUM
            ));
            passengerRepository.saveAll(passengers);
            TravelPackage travelPackageEntity = new TravelPackage("Adventure Getaway",
                    122,
                    destinations,
                    passengers);
            travelPackageRepository.save(travelPackageEntity);
        };
    }
    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}
}