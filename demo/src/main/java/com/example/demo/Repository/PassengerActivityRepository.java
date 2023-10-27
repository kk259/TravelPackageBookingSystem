package com.example.demo.Repository;

import com.example.demo.Entity.Passenger;
import com.example.demo.Entity.PassengerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PassengerActivityRepository extends JpaRepository<PassengerActivity, Integer> {
    @Query("select p from PassengerActivity p where p.passengerNumber = ?1")
    public Set<PassengerActivity> findPassengerActivitiesByPassengerNumber(int passengerNumber);
}

