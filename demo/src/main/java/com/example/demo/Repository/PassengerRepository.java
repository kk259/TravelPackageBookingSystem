package com.example.demo.Repository;

import com.example.demo.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    @Query("select p from Passenger p where p.passengerNumber = ?1")
    Passenger findPassengerByPassengerNumber(int passengerNumber);
}
