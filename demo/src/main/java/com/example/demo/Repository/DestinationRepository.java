package com.example.demo.Repository;

import com.example.demo.Entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    /*
    @Query("select t from TravelPackage t where t.travelPackageName = ?1")
    public TravelPackage findTravelPackageByTravelPackageName(String travelPackageName);
     */
}
